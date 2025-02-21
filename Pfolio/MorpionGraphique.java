import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MorpionGraphique extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3]; // Grille de 3x3 boutons
    private char currentPlayer = 'X'; // Le joueur humain
    private char computerPlayer = 'O'; // L'ordinateur
    private boolean gameRunning = true;

    public MorpionGraphique() {
        setTitle("Jeu du Morpion contre l'ordinateur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 3)); // Grille de 3x3

        // Initialiser la grille avec des boutons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this); // Écouteur d'événements
                add(buttons[row][col]); // Ajouter le bouton à la grille
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning) {
            return; // Arrêter si le jeu est terminé
        }

        JButton clickedButton = (JButton) e.getSource();

        // Vérifier si le bouton est déjà utilisé
        if (!clickedButton.getText().equals("") || currentPlayer != 'X') {
            return; // Si la case est déjà occupée ou ce n'est pas le tour du joueur
        }

        // Mettre à jour le texte du bouton avec le symbole du joueur humain
        clickedButton.setText(String.valueOf(currentPlayer));
        clickedButton.setEnabled(false); // Désactiver le bouton après clic

        // Vérifier si le joueur humain a gagné
        if (checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, "Le joueur 'X' a gagné !");
            gameRunning = false;
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Match nul !");
            gameRunning = false;
        } else {
            // Passer au tour de l'ordinateur
            currentPlayer = computerPlayer;
            computerMove(); // L'ordinateur joue
        }
    }

    private void computerMove() {
        Random random = new Random();
        boolean moveMade = false;

        // L'ordinateur choisit une case vide au hasard
        while (!moveMade) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(computerPlayer));
                buttons[row][col].setEnabled(false);
                moveMade = true;
                // Vérifier si l'ordinateur a gagné
                if (checkWin(computerPlayer)) {
                    JOptionPane.showMessageDialog(this, "L'ordinateur a gagné !");
                    gameRunning = false;
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(this, "Match nul !");
                    gameRunning = false;
                } else {
                    currentPlayer = 'X'; // Repasser au joueur humain
                }
            }
        }
    }

    private boolean checkWin(char player) {
        // Vérifier les lignes et les colonnes
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(player)) &&
                buttons[i][1].getText().equals(String.valueOf(player)) &&
                buttons[i][2].getText().equals(String.valueOf(player))) {
                return true;
            }

            if (buttons[0][i].getText().equals(String.valueOf(player)) &&
                buttons[1][i].getText().equals(String.valueOf(player)) &&
                buttons[2][i].getText().equals(String.valueOf(player))) {
                return true;
            }
        }

        // Vérifier les diagonales
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false; // Si une case est vide, le plateau n'est pas plein
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorpionGraphique jeu = new MorpionGraphique();
            jeu.setVisible(true);
        });
    }
}
