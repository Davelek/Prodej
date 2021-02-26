package view;

import javax.swing.*;
import java.awt.*;

public class BottomPanel {

    private JPanel mainPanel;
    private JButton btnBuy;
    private JButton btnAddToCart;

    private JLabel lbTotal;


    public BottomPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        btnBuy = new JButton("Koupit");
        btnAddToCart = new JButton("Přidat do košíku");

        lbTotal = new JLabel("0",SwingConstants.CENTER);


        this.initComponents();
    }

    private void initComponents() {
        mainPanel.add(lbTotal, BorderLayout.NORTH);
        mainPanel.add(btnAddToCart, BorderLayout.CENTER);
        mainPanel.add(btnBuy, BorderLayout.SOUTH);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getBtnBuy() {
        return btnBuy;
    }

    public void setBtnBuy(JButton btnBuy) {
        this.btnBuy = btnBuy;
    }

    public JButton getBtnAddToCart() {
        return btnAddToCart;
    }

    public void setBtnAddToCart(JButton btnAddToCart) {
        this.btnAddToCart = btnAddToCart;
    }

    public JLabel getLbTotal() {
        return lbTotal;
    }

    public void setLbTotal(JLabel lbTotal) {
        this.lbTotal = lbTotal;
    }
}
