<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi" lang="fi" dir="ltr>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset= "iso-8859-1" /> 
<meta HTTP-EQUIV="REFRESH" content="2; url=http://koti.mbnet.fi/kiiso/en-index.html">
<title>Thank you for your feedback.</title>
</head>
<body>


<?php

$ip = $_POST['ip']; 
$httpref = $_POST['httpref']; 
$httpagent = $_POST['httpagent']; 
$visitor = $_POST['visitor']; 
$visitormail = $_POST['visitormail']; 
$notes = $_POST['notes'];
$attn = $_POST['attn'];

if(!$visitormail == "" && (!strstr($visitormail,"@") || !strstr($visitormail,"."))) 
{
echo "<h2>Invalid e-mail, replace.</h2>\n"; 
$badinput = "<h2>The feedback could not be sent.</h2>\n";
echo $badinput;
die ("Back! ! ");
}

if(empty($visitor) || empty($visitormail) || empty($notes )) {
echo "<h2>Remember to fill all the fields!</h2>\n";
die ("Back! ! "); 
}

$todayis = date("l, F j, Y, g:i a") ;

$attn = $attn ; 
$subject = $attn; 

$notes = stripcslashes($notes); 

$message = " $todayis [EST] \n
Subject: $attn \n
Message: $notes \n 
Sender: $visitor ($visitormail)\n
Additional info: IP = $ip \n
Browser info: $httpagent \n
Reference: $httpref \n
";

$from = "From: $visitormail\r\n";


mail("aapokiiso@hotmail.com", $subject, $message, $from);

?>

<p align="center">
Date: <?php echo $todayis ?> 
<br />
Thank you: <?php echo $visitor ?> ( <?php echo $visitormail ?> ) 
<br />

Aihe: <?php echo $attn ?>
<br /> 
Viesti:<br /> 
<?php $notesout = str_replace("\r", "<br/>", $notes); 
echo $notesout; ?> 
<br />
<?php echo $ip ?>

<h2 align="center">Thank you for your feedback! </h2>



<br /><br />
<a href="koti.mbnet.fi/kiiso"> If your browser doesn't automatically redirect you, press here.</a> 
</p> 

</body>
</html>