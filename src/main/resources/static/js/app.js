// Fonction pour valider la confirmation de mot de passe
function validatePasswordConfirmation() {
	let idMotDePasse = document.getElementById("idMotDePasse").value;
	let idConfirmMotDePasse = document.getElementById("idConfirmMotDePasse").value;

	// Comparer le mot de passe et la confirmation de mot de passe
	if (idMotDePasse !== idConfirmMotDePasse) {
		// Afficher un message d'erreur
		document.getElementById("confirmMotDePasseError").innerText = "La confirmation de mot de passe ne correspond pas.";		
	} else {
		// Effacer le message d'erreur
		document.getElementById("confirmMotDePasseError").innerText = "";	
	}
}


document.getElementById("idConfirmMotDePasse").addEventListener("blur", validatePasswordConfirmation);