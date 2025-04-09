import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class MovieCollectionManager extends JFrame {
    private Movie[] movies = new Movie[1000];
    private int movieCount = 0;

    private JTextField titleField;
    private JComboBox<String> genreBox;
    private JTable movieTable;
    private DefaultTableModel tableModel;
    private JLabel countLabel;
    private JTextField searchField;

    public MovieCollectionManager() {
        setTitle("Movie Collection Manager 🎬🍿");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ----------- TOP PANEL (Input + Search) -----------
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 5, 5));

        // ADD MOVIE PANEL
        JPanel addPanel = new JPanel();
        titleField = new JTextField(15);
        genreBox = new JComboBox<>(new String[]{"Action", "Comedy", "Horror", "Drama", "Sci-Fi", "Romance"});
        JButton addButton = new JButton("Add Movie 🎥");

        addPanel.add(new JLabel("Title:"));
        addPanel.add(titleField);
        addPanel.add(new JLabel("Genre:"));
        addPanel.add(genreBox);
        addPanel.add(addButton);

        // SEARCH PANEL
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search 🔍");

        searchPanel.add(new JLabel("Search by Title:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        topPanel.add(addPanel);
        topPanel.add(searchPanel);

        add(topPanel, BorderLayout.NORTH);

        // ----------- CENTER (TABLE) -----------
        tableModel = new DefaultTableModel(new String[]{"Title", "Genre"}, 0);
        movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        // ----------- BOTTOM PANEL (Sort + Count) -----------
        JPanel controlPanel = new JPanel();
        JButton sortTitleBtn = new JButton("Sort by Title 🔤");
        JButton sortGenreBtn = new JButton("Sort by Genre 🎭");
        JButton countBtn = new JButton("Count Movies 🎬");
        countLabel = new JLabel("Total: 0");

        controlPanel.add(sortTitleBtn);
        controlPanel.add(sortGenreBtn);
        controlPanel.add(countBtn);
        controlPanel.add(countLabel);

        add(controlPanel, BorderLayout.SOUTH);

        // ----------- ACTIONS -----------

        // ADD MOVIE
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String genre = (String) genreBox.getSelectedItem();
            if (!title.isEmpty()) {
                movies[movieCount++] = new Movie(title, genre);
                updateTable();
                titleField.setText("");
            }
        });

        // SORT TITLE
        sortTitleBtn.addActionListener(e -> {
            Arrays.sort(movies, 0, movieCount, Comparator.comparing(Movie::getTitle, String.CASE_INSENSITIVE_ORDER));
            updateTable();
        });

        // SORT GENRE
        sortGenreBtn.addActionListener(e -> {
            Arrays.sort(movies, 0, movieCount, Comparator.comparing(Movie::getGenre, String.CASE_INSENSITIVE_ORDER));
            updateTable();
        });

        // COUNT MOVIES
        countBtn.addActionListener(e -> countLabel.setText("Total: " + movieCount));

        // SEARCH MOVIE AND MOVE TO TOP
        searchButton.addActionListener(e -> {
            String searchTitle = searchField.getText().trim().toLowerCase();
            if (searchTitle.isEmpty()) {
                updateTable();
                return;
            }

            Movie[] matches = new Movie[movieCount];
            Movie[] others = new Movie[movieCount];
            int matchIndex = 0;
            int otherIndex = 0;

            for (int i = 0; i < movieCount; i++) {
                if (movies[i].getTitle().toLowerCase().contains(searchTitle)) {
                    matches[matchIndex++] = movies[i];
                } else {
                    others[otherIndex++] = movies[i];
                }
            }

            if (matchIndex == 0) {
                JOptionPane.showMessageDialog(this, "Movie not found.");
                return;
            }

            int index = 0;
            for (int i = 0; i < matchIndex; i++) {
                movies[index++] = matches[i];
            }
            for (int i = 0; i < otherIndex; i++) {
                movies[index++] = others[i];
            }

            updateTable();
        });

        setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < movieCount; i++) {
            tableModel.addRow(new String[]{movies[i].getTitle(), movies[i].getGenre()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieCollectionManager::new);
    }
}

class Movie {
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
