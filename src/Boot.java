import model.Child;
import model.Customer;
import model.GenderType;
import model.ShoppingCart;
import view.BottomPanel;
import services.MyFile;
import view.ShoppingCartTable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.ArrayList;

public class Boot {
    private JFrame frame;
    private JFrame loginFrame;
    private Customer customer;
    private  JTable table;
    private ShoppingCartTable shoppingCartTable;
    private ShoppingCart shoppingCart;
    private ArrayList<Child> children;
    private Panel panel;
    private BottomPanel bottomPanel;

    public Boot() {
        shoppingCart = new ShoppingCart();
        try {
            loginFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            children =this.initChild();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        bottomPanel = new BottomPanel();
        frame = new JFrame();
        frame.setTitle("Kup si otroka");
        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // init

        this.initComponents();
        this.btnEvents();



        frame.pack();
        frame.setSize(new Dimension(1300, 800));
        frame.setMaximumSize(new Dimension(1300, 800));
        frame.setMinimumSize(new Dimension(1300, 800));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void btnEvents() {
        bottomPanel.getBtnAddToCart().addActionListener(e -> {
            if (table.getSelectedRow() != -1){

                for (Child child: shoppingCart.getChildList()
                     ) {
                        if (child == children.get(table.getSelectedRow())) {
                            shoppingCart.removeChild(child);
                            table.setValueAt(false,table.getSelectedRow(),7);
                            bottomPanel.getLbTotal().setText(Double.toString(shoppingCart.getTotal()));

                            return;
                        }
                }
                shoppingCart.addChild(children.get(table.getSelectedRow()));
                table.setValueAt(true,table.getSelectedRow(),7);
                bottomPanel.getLbTotal().setText(Double.toString(shoppingCart.getTotal()));
            }

        });

        bottomPanel.getBtnBuy().addActionListener(e -> {
            if (shoppingCart.getChildList().size() > 0){


            String row = shoppingCart.getCartId() + ",%,";
            for (Child child: shoppingCart.getChildList()
                 ) {
                row += child.getPersonalId() + ",";

            }
            row+= "%," + shoppingCart.getVat() + "," + shoppingCart.getSubtotal() + "," + shoppingCart.getTotal() + "," +
            customer.getCustomerId();

            try{
                MyFile.saveFile("orders.csv",row);
            }catch (IOException f){
                System.out.println("Chyba");
            }
                ArrayList<Child> newChildren = new ArrayList<>();
                for (Child item: children
                ) {
                    newChildren.add(item);

                }
                ArrayList<Integer> index = new ArrayList<>();
                for (Child item: shoppingCart.getChildList()
                     ) {
                    for (int i = 0; i < children.size(); i++) {
                        if (children.get(i).getPersonalId() == item.getPersonalId()){
                            index.add(i);
                        }
                    }
                }
                for (int i = newChildren.size()-1; i >=0 ; i--) {
                    for (int q = 0; q < index.size(); q++) {
                        if (index.get(q) == i){
                            newChildren.remove(i);
                        }
                    }
                }
                children = newChildren;
                shoppingCart.clearCart();
                shoppingCartTable.setChildren(children);
                bottomPanel.getLbTotal().setText(Double.toString(shoppingCart.getTotal()));
            }
            });
    }


    private ArrayList<Child> initChild() throws IOException {

        ArrayList<Child> deti = new ArrayList<>();
        for (String[] item : MyFile.ReadFile("MOCK_DATA.csv")) {
            Child child = new Child(item[0],item[1],Double.parseDouble(item[2]) ,Double.parseDouble(item[3]),item[4],
                    GenderType.valueOf(item[5]),Boolean.getBoolean(item[6]) ,(item[7]));
            deti.add(child);
        }
       return deti;
    }

    private void initComponents() {
        frame.getContentPane().add(bottomPanel.getMainPanel(), BorderLayout.SOUTH);
        try{
            shoppingCartTable = new ShoppingCartTable(children);
            table = new JTable(shoppingCartTable);
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage());
            System.exit(1);
        }
        shoppingCartTable.refreshTable();
frame.add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void loginFrame() throws IOException {
        loginFrame = new JFrame();
        loginFrame.setTitle("Kup si otroka");
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // init
        GridLayout loginLayout = new GridLayout(3,2);
        loginFrame.setLayout(loginLayout);

        JLabel lbUsername = new JLabel("Username:");
        JLabel lbPassword = new JLabel("Password:");
        JTextField tbUsername = new JTextField();
        JTextField tbPassword = new JTextField();
        JButton btnLogin = new JButton("Login");

        loginFrame.add(lbUsername);
        loginFrame.add(tbUsername);
        loginFrame.add(lbPassword);
        loginFrame.add(tbPassword);
        loginFrame.add(btnLogin);

        loginFrame.pack();
        loginFrame.setSize(new Dimension(200, 100));
       /* loginFrame.setMaximumSize(new Dimension(500, 300));
        loginFrame.setMinimumSize(new Dimension(500, 300));*/
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);

        btnLogin.addActionListener(e -> {
            ArrayList<Customer> customers = new ArrayList<>();
            try {
                for (String[] item : MyFile.ReadFile("customers.csv")) {
                    if (item[1].equals(tbUsername.getText()) && item[2].equals(tbPassword.getText())){
                        customer = new Customer(Integer.parseInt(item[0]),
                                shoppingCart,
                                item[1],
                                item[2],
                                item[3],
                                null,
                                Double.parseDouble(item[4]));
                        loginFrame.setVisible(false);
                        frame.setVisible(true);
//                        loginFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        break;
                    }
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(frame, ioException);
                System.exit(1);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Boot();
            }
        });
    }
}
