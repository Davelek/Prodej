package view;

import model.Child;
import model.GenderType;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartTable extends AbstractTableModel {


private List<Child> children;
private List<Boolean> isBoughtChildren;


    public ShoppingCartTable(List<Child> shoppingCart) {
        this.children = shoppingCart;
        isBoughtChildren = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            isBoughtChildren.add(false);
        }
    }



    public void setChildren(List<Child> children) {
        this.children = children;
        isBoughtChildren = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            isBoughtChildren.add(false);
        }
        refreshTable();
    }

    @Override
    public int getRowCount() {
        return children.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int index){
         switch (index) {
            case 0 : return "Name";

             case 1 : return "price";

             case 2 : return "weight";

             case 3 : return  "birthDate";

             case 4 : return  "gender";

             case 5 : return  "race";

             case 6 : return  "skinTone";
             case 7 : return  "V košíku";

             default : return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 : return String.class;

            case 1 : return Double.class;

            case 2 : return Double.class;

            case 3 : return String.class;

            case 4 : return GenderType.class;

            case 5 : return Boolean.class;

            case 6 : return String.class;

            case 7 : return Boolean.class;
            default : return null;
        }
    }

 /*   @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex ==7;
    }
*/
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Child child = children.get(rowIndex);
        switch (columnIndex) {
            case 0 : return child.getDisplayName();
            case 1 : return child.getPrice();
            case 2 : return child.getWeight();
            case 3 : return child.getBirthDate();
            case 4 : return child.getGender();
            case 5 : return child.isRace();
            case 6 : return child.getSkinTone();
            case 7 : return isBoughtChildren.get(rowIndex);


            default : return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 7){

            isBoughtChildren.set(rowIndex,(boolean) aValue) ;
        }
        refreshTable();
    }

    public void refreshTable(){
        fireTableDataChanged();
    }


}
