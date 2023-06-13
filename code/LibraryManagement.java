import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LibraryManagement extends JFrame implements ActionListener {
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
    private JPanel panel;
    private ArrayList<String[]> books = new ArrayList<>();
    private JFrame viewFrame;

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        Font font = new Font("Arial", Font.BOLD, 20);

        label1 = new JLabel("Book ID");
        label2 = new JLabel("Book Title");
        label3 = new JLabel("Author");
        label4 = new JLabel("Publisher");
        label5 = new JLabel("Year of Publication");
        label6 = new JLabel("ISBN");
        label7 = new JLabel("Number of Copies");

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);

        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);
        textField7 = new JTextField(20);

        textField1.setFont(font);
        textField2.setFont(font);
        textField3.setFont(font);
        textField4.setFont(font);
        textField5.setFont(font);
        textField6.setFont(font);
        textField7.setFont(font);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.setFont(font);
        viewButton.setFont(font);
        editButton.setFont(font);
        deleteButton.setFont(font);
        clearButton.setFont(font);
        exitButton.setFont(font);

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);
        gbc.gridx++;
        panel.add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label2, gbc);
        gbc.gridx++;
        panel.add(textField2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label3, gbc);
        gbc.gridx++;
        panel.add(textField3, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label4, gbc);
        gbc.gridx++;
        panel.add(textField4, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label5, gbc);
        gbc.gridx++;
        panel.add(textField5, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label6, gbc);
        gbc.gridx++;
        panel.add(textField6, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label7, gbc);
        gbc.gridx++;
        panel.add(textField7, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, gbc);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String[] book = new String[7];
            book[0] = textField1.getText();
            book[1] = textField2.getText();
            book[2] = textField3.getText();
            book[3] = textField4.getText();
            book[4] = textField5.getText();
            book[5] = textField6.getText();
            book[6] = textField7.getText();
            books.add(book);
            JOptionPane.showMessageDialog(this, "Book added successfully");
            clearFields();
        } else if (e.getSource() == viewButton) {
            String[] columns = {"Book ID", "Book Title", "Author", "Publisher", "Year of Publication", "ISBN", "Number of Copies"};
            String[][] data = new String[books.size()][7];

            for (int i = 0; i < books.size(); i++) {
                data[i] = books.get(i);
            }

            JTable table = new JTable(data, columns);
	    int spacing = 5; // Adjust the spacing value as needed
	    table.setIntercellSpacing(new Dimension(spacing, spacing));
            JScrollPane scrollPane = new JScrollPane(table);
            Font tableFont = table.getFont();
            Font newTableFont = new Font(tableFont.getName(), tableFont.getStyle(), 20);
            table.setFont(newTableFont);
	
	    int rowHeight = 30; // Adjust the row height as needed
table.setRowHeight(rowHeight);

// Increase column width
int columnWidth = 150; // Adjust the column width as needed
TableColumnModel columnModel = table.getColumnModel();
for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
    TableColumn column = columnModel.getColumn(columnIndex);
    column.setPreferredWidth(columnWidth);
}
	    

            // Set font size for table header
            JTableHeader tableHeader = table.getTableHeader();
            Font headerFont = tableHeader.getFont();
            Font newHeaderFont = new Font(headerFont.getName(), Font.BOLD, 20);
            tableHeader.setFont(newHeaderFont);

            JFrame frame = new JFrame("View Books");
            frame.add(scrollPane);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(this); // Center the frame on the main frame

            frame.setVisible(true);
        } else if (e.getSource() == editButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to edit:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookID)) {
                    String[] book = new String[7];
                    book[0] = bookID;
                    book[1] = textField2.getText();
                    book[2] = textField3.getText();
                    book[3] = textField4.getText();
                    book[4] = textField5.getText();
                    book[5] = textField6.getText();
                    book[6] = textField7.getText();
                    books.set(i, book);
                    JOptionPane.showMessageDialog(this, "Book updated successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        } else if (e.getSource() == deleteButton) {
            String bookID = JOptionPane.showInputDialog(this, "Enter book ID to delete:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookID)) {
                    books.remove(i);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book not found");
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void clearFields() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagement());
    }
}
