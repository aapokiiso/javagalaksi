<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fi" lang="fi" dir="ltr>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset= "iso-8859-1" /> 
<meta HTTP-EQUIV="REFRESH" content="2; url=http://koti.mbnet.fi/kiiso">
<title>Kiitos palautteestasi!</title>
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
echo "<h2>V‰‰r‰nlainen s-postiosoite, laita oikea.</h2>\n"; 
$badinput = "<h2>Palautetta ei kyetty l‰hett‰m‰‰n</h2>\n";
echo $badinput;
die ("Takaisin! ! ");
}

if(empty($visitor) || empty($visitormail) || empty($notes )) {
echo "<h2>Muista t‰ytt‰‰ kaikki kent‰t!</h2>\n";
die ("Takaisin! ! "); 
}

$todayis = date("l, F j, Y, g:i a") ;

$attn = $attn ; 
$subject = $attn; 

$notes = stripcslashes($notes); 

$message = " $todayis [EST] \n
Aihe: $attn \n
Viestie: $notes \n 
L‰hett‰j‰: $visitor ($visitormail)\n
Lis‰info : IP = $ip \n
Selaimen info: $httpagent \n
L‰hete : $httpref \n
";

$from = "From: $visitormail\r\n";


mail("aapokiiso@hotmail.com", $subject, $message, $from);

?>

<p align="center">
P‰iv‰m‰‰r‰: <?php echo $todayis ?> 
<br />
Kiitos : <?php echo $visitor ?> ( <?php echo $visitormail ?> ) 
<br />

Aihe: <?php echo $attn ?>
<br /> 
Viesti:<br /> 
<?php $notesout = str_replace("\r", "<br/>", $notes); 
echo $notesout; ?> 
<br />
<?php echo $ip ?>

<h2 align="center">Kiitos palautteestasi!</h2>



<br /><br />
<a href="koti.mbnet.fi/kiiso"> Jos selaimesi ei automaattisesti uudelleenohjaa sinua, paina t‰st‰. </a> 
</p> 

</body>
</html>