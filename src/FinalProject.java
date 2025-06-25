import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class GUI {
    private JFrame frame;
    private JList<Manufacturer> carList;
    private DefaultListModel<Manufacturer> carListModel;
    private JButton pitButton;
    private JComboBox<String> tyreComboBox;
    private JButton simulateLapButton;
    private String teamYouWorkFor;
    private int lapCounter = 0;
    private JLabel lapCounterLabel;
    private static final String LOG_FILENAME = "race_log.txt";
    private JButton showLogButton;
    private static final String LOG_FILE = "race_log.txt";
    private static final String[] TYRE_TYPES = {"Hard (H)", "Medium (M)", "Soft (S)"};

    public void createAndShowGUI(Manufacturer[] manufacturers) {
        try (FileWriter writer = new FileWriter(LOG_FILE, false)) {  // false = overwrite mode
            writer.write("");  // write empty string, clearing the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame = new JFrame("F1 Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        lapCounterLabel = new JLabel("Lap: 0", SwingConstants.CENTER);
        lapCounterLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(lapCounterLabel, BorderLayout.SOUTH);

        showLogButton = new JButton("Show Race Log");
        showLogButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Randomly pick your team
        int randomIndex = (int) (Math.random() * manufacturers.length);
        teamYouWorkFor = manufacturers[randomIndex].getBrand();

        // Title and label showing your team
        frame.setTitle("F1 Race - Your Team: " + teamYouWorkFor);
        JLabel teamLabel = new JLabel("You are working for: " + teamYouWorkFor, SwingConstants.CENTER);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 18));
        teamLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(teamLabel, BorderLayout.NORTH);

        // Populate list model and JList
        carListModel = new DefaultListModel<>();
        for (Manufacturer m : manufacturers) {
            carListModel.addElement(m);
        }

        carList = new JList<>(carListModel);
        carList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carList.setCellRenderer(new ManufacturerCellRenderer());
        carList.setSelectedIndex(0);

        // Select only your team in the list and disable selecting others
        for (int i = 0; i < carListModel.size(); i++) {
            Manufacturer m = carListModel.get(i);
            if (m.getBrand().equals(teamYouWorkFor)) {
                carList.setSelectedIndex(i);
                break;
            }
        }
        // Disable selection changes for other teams by listening to selection changes:
        carList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Manufacturer selected = carList.getSelectedValue();
                if (selected == null || !selected.getBrand().equals(teamYouWorkFor)) {
                    // Force selection back to your team
                    for (int i = 0; i < carListModel.size(); i++) {
                        if (carListModel.get(i).getBrand().equals(teamYouWorkFor)) {
                            carList.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        JScrollPane listScrollPane = new JScrollPane(carList);
        listScrollPane.setPreferredSize(new Dimension(300, 0));
        frame.add(listScrollPane, BorderLayout.WEST);

        // Right panel with pit button, tyre combo box, and simulate button
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pitButton = new JButton("Pit");
        pitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        tyreComboBox = new JComboBox<>(TYRE_TYPES);
        tyreComboBox.setEnabled(false);
        tyreComboBox.setMaximumSize(new Dimension(200, 30));
        tyreComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        simulateLapButton = new JButton("Simulate Lap Times");
        simulateLapButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(pitButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(tyreComboBox);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(simulateLapButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(showLogButton);

        frame.add(rightPanel, BorderLayout.EAST);

        // Pit button toggles tyreComboBox enabled and sets current tyre selection
        pitButton.addActionListener(e -> {

            Manufacturer selectedCar = carList.getSelectedValue();
            if (selectedCar == null || !selectedCar.getBrand().equals(teamYouWorkFor)) {
                return;  // Only allow your team's car
            }
            boolean enabled = !tyreComboBox.isEnabled();
            tyreComboBox.setEnabled(enabled);
            if (!enabled) {
                tyreComboBox.setSelectedIndex(-1);
            } else {
                char tyreType = selectedCar.getTyre().getType();
                int index = switch (tyreType) {
                    case 'H' -> 0;
                    case 'M' -> 1;
                    case 'S' -> 2;
                    default -> -1;
                };
                tyreComboBox.setSelectedIndex(index);
            }
        });

        // Changing tyreComboBox updates the tyre of the selected car (only if enabled)
        tyreComboBox.addActionListener(e -> {
            if (!tyreComboBox.isEnabled()) return;

            Manufacturer selectedCar = carList.getSelectedValue();
            if (selectedCar == null || !selectedCar.getBrand().equals(teamYouWorkFor)) {
                return;
            }
            String selectedTyre = (String) tyreComboBox.getSelectedItem();
            if (selectedTyre == null) return;
            char tyreCode = selectedTyre.charAt(selectedTyre.indexOf('(') + 1);
            selectedCar.setTyre(new Tyre(tyreCode, 15, 15));
            carList.repaint();
        });


        // Simulate lap times for all cars
        simulateLapButton.addActionListener(e -> {
            StringBuilder result = new StringBuilder("Lap Times:\n\n");

            lapCounter++;
            lapCounterLabel.setText("Lap: " + lapCounter);
            for (int i = 0; i < carListModel.size(); i++) {
                Manufacturer m = carListModel.get(i);
                Tyre tyre = m.getTyre();
                tyre.life--;
                if (m.getBrand().equals(teamYouWorkFor)) {

                    if (tyre.getLife() <= 0) {
                            JOptionPane.showMessageDialog(frame,
                                    "Your car's tyre has worn out! New tyre compound assigned: ",
                                    "Tyre Change Notification",
                                    JOptionPane.INFORMATION_MESSAGE);


                        m.pitPenalty = 4;
                    }

                }

                else {
                    if (tyre.getLife() <= 0) {
                        Tyre newTyre = getRandomTyre();
                        m.setTyre(newTyre);
                        m.pitPenalty = 4;
                    }
                }


                }


            // 1. Calculate lap times and store in each Manufacturer
            for (int i = 0; i < carListModel.size(); i++) {
                Manufacturer m = carListModel.get(i);
                int speed = m.getSpeed();
                int aero = m.getAero();
                int weight = m.getWeight();
                int grip = m.getTyre().getGrip();

                double baseTime = 100.0;
                double performance = speed * 0.8 + aero * 0.6 + grip * 0.7;
                double penalty = weight * 0.05;
                double randomness = Math.random() * 4 - 2;  // ±2 seconds randomness


                double lapTime = baseTime - performance + penalty + randomness;

                if(m.pitPenalty > 0){
                    lapTime = lapTime + 8;
                    m.pitPenalty--;
                }


                lapTime = Math.round(lapTime * 100.0) / 100.0;

                m.lapTime = lapTime; // Store in object
            }

            try {
                FileWriter writer = new FileWriter(LOG_FILE, true); // true = append mode
                writer.write("Lap " + lapCounter + ":\n");

                for (int i = 0; i < carListModel.size(); i++) {
                    Manufacturer m = carListModel.get(i);
                    writer.write((i + 1) + ". " + m.getBrand() +
                            " - Lap Time: " + m.lapTime + "s, Tyre: " + m.getTyre().getType() + "\n");
                }

                writer.write("\n"); // Blank line between laps
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (lapCounter >= 30) {
                JOptionPane.showMessageDialog(frame, "Race is over! Please restart the application.", "Race Finished", JOptionPane.INFORMATION_MESSAGE);
                StringBuilder podium = new StringBuilder("🏁 Race Finished!\n\nTop 3:\n");
                podium.append("1st: ").append(carListModel.get(0).getBrand()).append("\n");
                podium.append("2nd: ").append(carListModel.get(1).getBrand()).append("\n");
                podium.append("3rd: ").append(carListModel.get(2).getBrand()).append("\n");

                JOptionPane.showMessageDialog(frame, podium.toString(), "Final Standings", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 2. Overtake logic: compare adjacent cars
            for (int i = carListModel.size() - 1; i > 0; i--) {
                Manufacturer behind = carListModel.get(i);
                Manufacturer ahead = carListModel.get(i - 1);

                double timeDiff = ahead.lapTime - behind.lapTime;

                if (timeDiff > 1.5) { // Must be significantly faster
                    double overtakeChance = Math.min(0.9, 0.1 + timeDiff * 0.3); // More gap = more chance
                    double roll = Math.random();
                    if (roll < overtakeChance) {
                        // Swap in model
                        carListModel.set(i, ahead);
                        carListModel.set(i - 1, behind);
                        result.append("[OVERTAKE] ").append(behind.getBrand())
                                .append(" overtook ").append(ahead.getBrand())
                                .append(" (Δ ").append(String.format("%.2f", timeDiff)).append("s)\n");
                    }
                }
            }

            // 3. Show results after possible overtakes
            result.append("\nUpdated Positions:\n\n");
            for (int i = 0; i < carListModel.size(); i++) {
                Manufacturer m = carListModel.get(i);
                result.append((i + 1)).append(". ").append(m.getBrand())
                        .append(" - Lap Time: ").append(m.lapTime).append(" sec\n");
            }

            JOptionPane.showMessageDialog(frame, result.toString(), "Simulated Lap Times", JOptionPane.INFORMATION_MESSAGE);
            carList.repaint(); // Update GUI display order

        });

        showLogButton.addActionListener(e -> {
            StringBuilder logContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILENAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logContent.append(line).append("\n");
                }
            } catch (IOException ex) {
                logContent.append("No race log available or error reading the file.");
            }
            JTextArea textArea = new JTextArea(logContent.toString());
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(frame, scrollPane, "Race Log", JOptionPane.INFORMATION_MESSAGE);
        });




        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }






    private static class ManufacturerCellRenderer extends JLabel implements ListCellRenderer<Manufacturer> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Manufacturer> list, Manufacturer value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setOpaque(true);
            String tyreType = switch (value.getTyre().getType()) {
                case 'H' -> "Hard";
                case 'M' -> "Medium";
                case 'S' -> "Soft";
                default -> "Unknown";
            };
            setText((index + 1) + ". " + value.getBrand() + " - Tyre: " + tyreType);

            if (isSelected) {
                setBackground(Color.BLUE);
                setForeground(Color.WHITE);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            return this;
        }
    }

    private static Tyre getRandomTyre() {
        char[] tyreTypes = {'H', 'M', 'S'};
        int randomIndex = (int) (Math.random() * tyreTypes.length);
        return new Tyre(tyreTypes[randomIndex], 15, 15);
    }
}


class FinalProject  {
    public static void main(String[] args) {



        Manufacturer mercedes = new Manufacturer("Mercedes", 0, getRandomTyre());
        mercedes.weight = 690;
        Manufacturer redBull = new Manufacturer("Red Bull", 0, getRandomTyre());
        redBull.aero = 20;
        Manufacturer mclaren = new Manufacturer("McLaren", 0, getRandomTyre());
        mclaren.aero = 18;
        mclaren.speed = 18;
        Manufacturer ferrari = new Manufacturer("Ferrari", 0, getRandomTyre());
        ferrari.speed = 20;
        Manufacturer astonMartin = new Manufacturer("Aston Martin", 0, getRandomTyre());
        Manufacturer alpine = new Manufacturer("Alpine", 0, getRandomTyre());
        Manufacturer williams = new Manufacturer("Williams", 0, getRandomTyre());
        Manufacturer haas = new Manufacturer("Haas", 0, getRandomTyre());
        Manufacturer racingBulls = new Manufacturer("Racing Bulls", 0, getRandomTyre());
        Manufacturer stakeSauber = new Manufacturer("Stake Sauber", 0, getRandomTyre());

        Manufacturer[] manufacturers = new Manufacturer[] {
                mercedes,
                redBull,
                mclaren,
                ferrari,
                astonMartin,
                alpine,
                williams,
                haas,
                racingBulls,
                stakeSauber
        };


        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.createAndShowGUI(manufacturers);

        });


    }

    private static Tyre getRandomTyre() {
        char[] tyreTypes = {'H', 'M', 'S'};
        int randomIndex = (int) (Math.random() * tyreTypes.length);
        return new Tyre(tyreTypes[randomIndex], 15, 15);
    }

}

class F1 {
    int speed;
    int aero;
    int weight;
    Tyre tyre;
    int pitPenalty;

    public F1(int speed, int aero, int weight, Tyre tyre,  int pitPenalty) {
        this.speed = speed;
        this.aero = aero;
        this.weight = weight;
        this.tyre = tyre;
        this.pitPenalty = pitPenalty;
    }

    public int getSpeed() {
        return speed;
    }
    public int getAero() {
        return aero;
    }
    public int getWeight() {
        return weight;
    }
    public Tyre getTyre() {
        return tyre;
    }
}

class Manufacturer extends F1 {
    String brand;
    double lapTime;
    public Manufacturer (String brand,double lapTime, Tyre tyre) {
        super(15,15,700, tyre, 0);
        this.brand = brand;
        this.lapTime = lapTime;

    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public String getBrand() {
        return brand;
    }
}

class Tyre {
    char type;
    int life;
    int grip;

    public Tyre(char type, int life, int grip) {
        this.type = type;
        this.life = 15;
        this.grip = 15;
    }

    public char getType() {
        return type;
    }

    public int getLife() {
        if(getType() == 'H') {
            return life;
        } else {
            if(getType() == 'M') {
                return life - 5;
            } else if (getType() == 'S') {
                return life-10;
            }

        }

        return 0;
    }

    public int getGrip() {
        if(getType() == 'H')
            return grip - 10;

        else {
            if(getType() == 'M') {
                return grip-5;
            } else if (getType() == 'S') {
                return grip;
            }
        }

        return 0;
    }

}


