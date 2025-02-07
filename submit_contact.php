<?php
// Paramètres de connexion à la base de données
$servername = "localhost";
$username = "u937355202_SeydouD";
$password = "Seydo2745$";
$dbname = "u937355202_SeydouDBDD";

// Créer une connexion
$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die("Erreur de connexion : " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Nettoyer et sécuriser les données du formulaire
    $name = htmlspecialchars($_POST['name']);
    $email = htmlspecialchars($_POST['email']);
    $message = htmlspecialchars($_POST['message']);

    // Préparer la requête SQL pour insérer les données
    $sql = "INSERT INTO contacts (name, email, message, created_at) VALUES (?, ?, ?, NOW())";
    
    // Préparer et lier les paramètres
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("sss", $name, $email, $message);

    // Exécuter la requête
    if ($stmt->execute()) {
        // Envoyer un e-mail
        $to = "seydou.diallopro@outlook.fr";
        $subject = "Nouveau message de contact";
        $email_headers = "From: $email";

        if (mail($to, $subject, $message, $email_headers)) {
            echo "Message enregistré et envoyé avec succès.";
        } else {
            echo "Message enregistré, mais erreur lors de l'envoi de l'e-mail.";
        }
    } else {
        echo "Erreur lors de l'enregistrement du message : " . $stmt->error;
    }

    // Fermer la déclaration et la connexion
    $stmt->close();
    $conn->close();
}
?>