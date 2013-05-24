package ibfb.nursery.models;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class GermplasmEntriesTableModel extends AbstractTableModel {

    public static final String PLANTS_SELECTED = "PLANTSSELECTEDNUMBER";
    public static final String ENTRY = "GERMPLASMENTRYNUMBER";
    public static final String ENTRY_CODE = "GERMPLASMENTRYCODE";
    public static final String DESIG = "GERMPLASMIDDBCV";
    public static final String GID = "GERMPLASMIDDBID";
    public static final String PLOT = "FIELDPLOTNESTEDNUMBER";
    public static final String CROSS = "CROSSNAMENUMBER";
    public static final String CROSSCIMMYTWHEAT = "CROSSNAMENAME";
    public static final String ISCHECKNUMBER = "CHECKCODE";
    ///--------
    // Traits for inventory
    public static final String AMOUNT = "AMOUNT";
    public static final String SCALE = "SCALE";
    public static final String COMMENT = "COMMENT";
    public static final String LOCATION = "LOCATION";
    //  public static final String BCID = "GERMPLASMBCIDDBID";
    private boolean hasChecks = false;
    private List<Factor> factorHeaders;
    private List<List<Object>> germplasmData;
    private String[] checkHeaders = {"Initial position", "Frequency"};
    private boolean isForInventory = false;
    private boolean enabledLocation = false;
    private boolean enabledComments = false;
    private boolean enabledAmount = false;
    private boolean enabledScale = false;
    private boolean seActualizaGID = false;
    /**
     * Mark germplasm entries as Checks?
     */
    private boolean markGermplasmAsCheck = false;
    /**
     * To easy retrieving of column indexes
     */
    private HashMap<String, Integer> headerIndex = new HashMap<String, Integer>();
    public static boolean isFromCrossInfo = false;
    /**
     * List of items containing all headers. Items in list can be Factor o
     * Variates
     */
    private List<Object> headers;

    public static void setIsFromCrossInfo(boolean isFromCrossInfo) {
        GermplasmEntriesTableModel.isFromCrossInfo = isFromCrossInfo;
    }

    public boolean isEnabledAmount() {
        return enabledAmount;
    }

    public boolean isEnabledScale() {
        return enabledScale;
    }

    public boolean isEnabledComments() {
        return enabledComments;
    }

    public boolean SeActualizaGID() {
        return seActualizaGID;
    }

    public void setSeActualizaGID(boolean seActualizaGID) {
        this.seActualizaGID = seActualizaGID;
    }

    public void setEnabledComments(boolean enabledComments) {
        this.enabledComments = enabledComments;
    }

    public boolean isEnabledLocation() {
        return enabledLocation;
    }

    public void setEnabledLocation(boolean enabledLocation) {
        this.enabledLocation = enabledLocation;
    }

    public void setEnabledAmount(boolean enabledamount) {
        this.enabledAmount = enabledamount;
    }

    public void setEnabledScale(boolean enabledSclae) {
        this.enabledScale = enabledSclae;
    }

    public GermplasmEntriesTableModel() {
        clearTable();
        assignHeaders();
    }

    public GermplasmEntriesTableModel(List<Factor> factorHeaders, List<List<Object>> germplasmData) {
        this.factorHeaders = factorHeaders;
        this.germplasmData = germplasmData;
        assignHeaders();

    }

    public boolean IsForInventory() {
        return isForInventory;
    }

    public void setIsForInventory(boolean isForInventory) {
        this.isForInventory = isForInventory;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (SeActualizaGID()) {
            if (columnIndex == 2) {
                return true;
            }

        }

        if (IsForInventory()) {

            if (columnIndex == getLocationColumn()) {
                if (isEnabledLocation()) {
                    return false;//true;
                } else {
                    return false;
                }

            } else if (columnIndex == getCommentColumn()) {
                if (isEnabledComments()) {
                    return true;
                } else {
                    return false;
                }

            } else if (columnIndex == getAmountColumn()) {
                if (isEnabledAmount()) {
                    return true;
                } else {
                    return false;
                }

            } else if (columnIndex == getScaleColumn()) {
                if (isEnabledScale()) {
                    return false;//true;
                } else {
                    return false;
                }
            }

        }


        if (!hasChecks) {
            return false;
        } else {
            if (columnIndex > factorHeaders.size() - 1) {
                return false;  //true if position and frequency will be editables
            }
        }

        return super.isCellEditable(rowIndex, columnIndex);
    }

    public void setHasChecks(boolean hasChk) {
        this.hasChecks = hasChk;
    }

    @Override
    public int getRowCount() {
        return germplasmData.size();
    }

    @Override
    public int getColumnCount() {
        if (!hasChecks) {
            return factorHeaders.size();
        } else {
            return factorHeaders.size() + 2;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (!hasChecks) {
            return factorHeaders.get(column).getFactorName();
        } else {

            if (column < factorHeaders.size()) {
                return factorHeaders.get(column).getFactorName();
            } else {
                return checkHeaders[column - factorHeaders.size()];
            }

        }


    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Object> columnValues = germplasmData.get(rowIndex);
        if (columnIndex < columnValues.size()) {
            return columnValues.get(columnIndex);
        } else {
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {



        if (isFromCrossInfo) {
            List<Object> columnValues = germplasmData.get(rowIndex);
            columnValues.set(columnIndex, aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }


        if (SeActualizaGID()) {

            List<Object> columnValues = germplasmData.get(rowIndex);
            columnValues.set(columnIndex, aValue);
            fireTableCellUpdated(rowIndex, columnIndex);


        }



        if (IsForInventory()) {
            if (columnIndex > 2) {
                List<Object> columnValues = germplasmData.get(rowIndex);
                columnValues.set(columnIndex, aValue);
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }


        if (hasChecks) {

            List<Object> columnValues = germplasmData.get(rowIndex);

            columnValues.set(columnIndex, aValue);


            fireTableCellUpdated(rowIndex, columnIndex);
        }

        if (markGermplasmAsCheck) {
            List<Object> columnValues = germplasmData.get(rowIndex);

            columnValues.set(columnIndex, aValue);


            fireTableCellUpdated(rowIndex, columnIndex);

        }

    }

    public List<Factor> getFactorHeaders() {
        return factorHeaders;
    }

    public List<List<Object>> getGermplasmData() {
        return germplasmData;
    }

    public void clearTable() {
        factorHeaders = new ArrayList<Factor>();
        germplasmData = new ArrayList<List<Object>>();
        fireTableDataChanged();
    }

    /**
     * Assign headers from template
     */
    private void assignHeaders() {
        headers = new ArrayList<Object>();

        int columnIndex = 0;

        // add headers from factor section which are TRIAL
        for (Factor factor : factorHeaders) {
            headers.add(factor);
            String headerName = Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()).toUpperCase();
            headerIndex.put(headerName, columnIndex);
            columnIndex++;
        }

    }

    /**
     * Get the column index for header
     *
     * @param columnName Column name to search
     * @return column index number greater than 0 if found or -1 if not found
     */
    public int getHeaderIndex(String columnName) {
        int columnIndex = -1;
        if (headerIndex.get(columnName) != null) {
            columnIndex = headerIndex.get(columnName);
        }
        return columnIndex;
    }

    public String getEntryLabel() {
        String entryLabel = "ENTRY";
        for (Factor factor : factorHeaders) {
            if (Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()).equals(ENTRY)) {
                entryLabel = factor.getFactorName();
                break;
            }
        }
        return entryLabel;
    }

    public String getPlotLabel() {
        String plotLabel = "PLOT";
        for (Factor factor : factorHeaders) {
            if (Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()).equals(PLOT)) {
                plotLabel = factor.getFactorName();
                break;
            }
        }
        return plotLabel;
    }

    /**
     * Adds a new facto to identify a factor of type check
     */
    public void addCheckFactor(Factor factor) {
        if (!factorHeaders.contains(factor)) {
            int columnIndex = headers.size();
            factorHeaders.add(factor);
            headers.add(factor);
            headerIndex.put(Workbook.getStringWithOutBlanks(factor.getProperty() + factor.getScale()), columnIndex);
            fireTableStructureChanged();
        }
    }

    public boolean isMarkGermplasmAsCheck() {
        return markGermplasmAsCheck;
    }

    public void setMarkGermplasmAsCheck(boolean markGermplasmAsCheck) {
        this.markGermplasmAsCheck = markGermplasmAsCheck;
    }

    private int getAmountColumn() {
        return getColumnIndex(AMOUNT);
    }

    private int getLocationColumn() {
        return getColumnIndex(LOCATION);
    }

    private int getCommentColumn() {
        return getColumnIndex(COMMENT);
    }

    private int getScaleColumn() {
        return getColumnIndex(SCALE);
    }

    private int getColumnIndex(String factorName) {
        int columnIndex = -1;

        for (int col = 0; col < factorHeaders.size(); col++) {
            if (factorHeaders.get(col).getFactorName().equals(factorName)) {
                columnIndex = col;
                break;
            }
        }

        return columnIndex;

    }
}
