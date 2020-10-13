<?php

// Replace this with your own email address
$siteOwnersEmail = 'anthony.pellizzeri3@orange.fr';


if($_POST) {

   $name = trim(stripslashes($_POST['contactName']));
   $email = trim(stripslashes($_POST['contactEmail']));
   $subject = trim(stripslashes($_POST['contactSubject']));
   $contact_message = trim(stripslashes($_POST['contactMessage']));

   // Check Name
	if (strlen($name) < 2) {
		$error['name'] = "entrez votre nom.";
	}
	// Check Email
	if (!preg_match('/^[a-z0-9&\'\.\-_\+]+@[a-z0-9\-]+\.([a-z0-9\-]+\.)*+[a-z]{2}/is', $email)) {
		$error['email'] = "Entrez une adresse mail valide.";
	}
	// Check Message
	if (strlen($contact_message) < 5) {
		$error['message'] = "Entrez votre message. Il doit contenir au moins 5 caractères";
	}
   // Subject
	if ($subject == '') { $subject = "Contact pas defini"; }


   // Set Message
   $message .= "email de: " . $name;
    $message .= "Message: ";
	$message .= "  Adresse mail : " . $email;
   $message .= $contact_message;

   // Set From: header
   $from =  $name . " <" . $email . ">";

   // Email Headers
	$headers = "DE: " . $from . "\r\n";
	$headers .= "Répondre à: ". $email . "\r\n";


   if (!$error) {

      ini_set("sendmail_from", $siteOwnersEmail); // for windows server
      $mail = mail($siteOwnersEmail, $subject, $message, $headers);

		if ($mail) { echo "OK"; }
      else { echo "Il y a eu une erreur, resaisissez votre demande."; }
		
	} # end if - no validation error

	else {

		$response = (isset($error['name'])) ? $error['name'] . "<br /> \n" : null;
		$response .= (isset($error['email'])) ? $error['email'] . "<br /> \n" : null;
		$response .= (isset($error['message'])) ? $error['message'] . "<br />" : null;
		
		echo $response;

	} # end if - there was a validation error

}

?>