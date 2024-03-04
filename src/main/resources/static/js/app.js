// Fonction pour valider la confirmation de mot de passe
function validatePasswordConfirmation() {
    let idMotDePasse = document.getElementById("idMotDePasse").value;
    let idConfirmMotDePasse = document.getElementById("idConfirmMotDePasse").value;

    // Comparer le mot de passe et la confirmation de mot de passe
    if (idMotDePasse !== idConfirmMotDePasse) {
        // Afficher un message d'erreur
        document.getElementById("confirmMotDePasseError").innerText = "La confirmation de mot de passe ne correspond pas.";
        return false; // Empêcher la soumission du formulaire
    } else {
        // Effacer le message d'erreur
        document.getElementById("confirmMotDePasseError").innerText = "";
        return true; // Autoriser la soumission du formulaire
    }
}


document.getElementById("idConfirmMotDePasse").addEventListener("blur", validatePasswordConfirmation);