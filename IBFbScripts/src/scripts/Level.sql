DELIMITER $$

DROP PROCEDURE IF EXISTS `getLevelsByLabelId`$$

CREATE PROCEDURE `getLevelsByLabelId`(IN p_labelid int, IN isnumeric int, IN iscentral int)
BEGIN
IF iscentral = 1 THEN

    SELECT
      labelid
      , factorid
      , levelno
      , lvalue
    FROM 
       v_level
    WHERE (isnumeric AND dtypeid NOT IN (1120, 1128)
      OR NOT isnumeric AND dtypeid IN (1128, 1120))
      AND labelid = p_labelid
    ORDER BY levelno ASC
    ;

ELSE

    SELECT
      labelid
      , factorid
      , levelno
      , lvalue
    FROM 
       v_level
    WHERE (isnumeric AND dtypeid NOT IN (1120, 1128)
      OR NOT isnumeric AND dtypeid IN (1128, 1120))
      AND labelid = p_labelid
    ORDER BY levelno DESC
    ;


END IF;
END$$


DROP PROCEDURE IF EXISTS `searchLevels`$$

CREATE PROCEDURE `searchLevels`(
  IN labelid int
  , IN levelno int
  , IN factorid int
  , IN lvalue1 double
  , IN lvalue2 varchar(255)
  , IN isnumeric int
  , IN iscentral int)

BEGIN

  SET @sql := CONCAT("SELECT labelid AS labelid, factorid, levelno AS levelno, lvalue from v_level ");

  IF isnumeric = 1 THEN
    SET @sql = CONCAT(@sql, " WHERE dtypeid NOT IN (1120, 1128) ");
  ELSE
    SET @sql = CONCAT(@sql, " WHERE dtypeid IN (1120, 1128) ");
  END IF;

  IF (labelid IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND labelid = ", labelid);
  END IF;

  IF (levelno IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND levelno = ", levelno);
  END IF;

  IF (factorid IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND factorid = ", factorid);
  END IF;
  
  IF (lvalue1 IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND lvalue = ", lvalue1);
  END IF;

  IF (lvalue2 IS NOT NULL) THEN
    SET @sql = CONCAT(@sql, " AND lvalue = ", lvalue2);
  END IF;

  IF iscentral = 1 THEN
    SET @sql = CONCAT(@sql, " ORDER BY labelid, levelno ASC");
  ELSE
    SET @sql = CONCAT(@sql, " ORDER BY labelid, levelno DESC");
  END IF;

  PREPARE stmt FROM @sql;
  EXECUTE stmt;


END$$



