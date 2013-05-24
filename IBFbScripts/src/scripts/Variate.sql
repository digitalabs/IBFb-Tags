delimiter $$

drop procedure if EXISTS getVariateById$$

CREATE PROCEDURE getVariateById(IN v_variatid int)
begin

	SELECT variatid, vname 
	,studyid 
	,GROUP_CONCAT(if(relationship = 1200, ontology_id, NULL)) AS 'traitid' 
	,GROUP_CONCAT(if(relationship = 1220, ontology_id, NULL)) AS 'scaleid' 
	,GROUP_CONCAT(if(relationship = 1210, ontology_id, NULL)) AS 'tmethid' 
	,GROUP_CONCAT(if(relationship = 1225, ontology_value, NULL)) AS 'vtype' 
	,GROUP_CONCAT(if(relationship = 1105, ontology_value, NULL)) AS 'dtype' 
	,GROUP_CONCAT(if(relationship = 1044, ontology_id, NULL)) AS 'tid' 
	FROM 
	(SELECT pp.projectprop_id as variatid 
	,label.value as vname 
	,cvtr.type_id as relationship 
	,cvt3.cvterm_id as ontology_id 
	,cvt3.name as ontology_value	
	,pr.object_project_id as studyid 
	FROM cvterm cvt1 
	INNER JOIN cvterm_relationship cvtr ON cvt1.cvterm_id = cvtr.subject_id 
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
	INNER JOIN projectprop pp ON pp.value = cvt1.cvterm_id 
	INNER JOIN projectprop label ON label.project_id = pp.project_id AND label.rank = pp.rank 
	INNER JOIN project_relationship pr ON pr.subject_project_id = pp.project_id AND pr.type_id = 1150
	WHERE pp.type_id = 1070 AND label.type_id in (1043,1048) AND pp.projectprop_id = v_variatid 
	AND EXISTS ( SELECT 1 FROM phenotype ph WHERE ph.observable_id = pp.value ) 
	GROUP BY variatid, cvtr.type_id, cvt3.cvterm_id, cvt3.name, pp.project_id
	) as variate;
	
end$$

drop procedure if EXISTS addVariate$$

CREATE PROCEDURE addVariate(
IN v_studyid int,
IN v_vname varchar(50),
IN v_traitid int,
IN v_scaleid int,
IN v_tmethid int,
IN v_dtype int,
IN v_vtype int,
IN v_tid int)
begin

DECLARE v_project_id int;
DECLARE v_projectprop_id int;
DECLARE v_rank int;
DECLARE v_type_id int;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

	SELECT MAX(rank) + 1 as rank INTO v_rank 
	FROM projectprop pp
	WHERE pp.project_id = v_studyid;
	
	IF(v_rank IS NULL) THEN
	SET v_rank := 1;
	END IF;
	
	SET v_project_id := v_studyid;
	
	CALL getNextMinReturn('projectprop',v_projectprop_id);

	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,v_tid,v_vname,v_rank);
	
	CALL getNextMinReturn('projectprop',v_projectprop_id);
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,1060,v_vname,v_rank);
	
	SELECT cvt1.cvterm_id into v_type_id
    FROM cvterm cvt1 
    WHERE EXISTS (
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvtr.type_id = 1044 AND cvt3.cvterm_id = v_tid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvtr.type_id = 1200 AND cvt3.cvterm_id = v_traitid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvtr.type_id = 1220 AND cvt3.cvterm_id = v_scaleid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvtr.type_id = 1210 AND cvt3.cvterm_id = v_tmethid)
    );
    
	CALL getNextMinReturn('projectprop',v_projectprop_id);
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,1070,v_type_id,v_rank);
	
	SELECT v_projectprop_id;

COMMIT;	
	
end$$




DROP PROCEDURE IF EXISTS `getVarieteFromVeffects`$$

CREATE PROCEDURE `getVarieteFromVeffects`(IN p_represno int)

BEGIN

  SELECT
    pp.projectprop_id AS variatid
    , pr.object_project_id AS studyid
    , term.value AS vname
    , GROUP_CONCAT(IF(cvr.type_id = 1200, cvr.object_id, NULL)) AS traitid
    , GROUP_CONCAT(IF(cvr.type_id = 1220, cvr.object_id, NULL)) AS scaleid
    , GROUP_CONCAT(IF(cvr.type_id = 1210, cvr.object_id, NULL)) AS tmethid
    , GROUP_CONCAT(IF(cvr.type_id = 1105, IF(cvr.object_id IN (1120, 1128), 'C', 'N'), NULL)) AS dtype
    , GROUP_CONCAT(IF(cvr.type_id = 1225, obj.name, NULL)) AS vtype
    , GROUP_CONCAT(IF(cvr.type_id = 1044, cvr.object_id, NULL)) AS tid
  FROM
    projectprop pp
    INNER JOIN project_relationship pr ON pr.type_id = 1150 AND pr.subject_project_id = pp.project_id
    INNER JOIN cvterm_relationship cvr ON cvr.subject_id = pp.value
    INNER JOIN cvterm obj ON obj.cvterm_id = cvr.object_id
    INNER JOIN projectprop term ON term.project_id = pp.project_id AND term.rank = pp.rank AND term.type_id IN (1043, 1048)
  WHERE
    pp.type_id = 1070 
    AND pp.project_id = p_represno
  GROUP BY
    pp.projectprop_id
  ;

END$$
