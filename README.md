# AccountApp
An Android MySQL integrated login and registration app with multi-linguistic capability.

© May 2021 by [@ddisPupi](https://github.com/addispupi/), Please use it wisely.

## Initialization File **(int.php)**
> 	
	<?php
		$name = "account_app";
		$user = "root";
		$pass = "";
		$server = "localhost";

		$conn = mysqli_connect($server, $user, $pass, $name);

			if(!$conn)
			   echo "Connection Error: ".mysqli_connect_error();
			else
			   echo "Connection Succeeded !";
	?>
>

## Register Page (mod_register.php)
>
	<?php
		require('config/init.php');
		$firstName = $_POST['firstName'];
		$lastName = $_POST['lastName'];
		$userEmail = $_POST['userEmail'];
		$userPass = md5($_POST['userPass']);

		$sqlRegister = "INSERT into users_acc VALUES(null, '$firstName', '$lastName', '$userEmail', '$userPass')";
		mysqli_query($conn, $sqlRegister);
			if(mysqli_query($conn, $sqlRegister)){
			  echo "<h1>User Successfully Registered !</h1>";
			}
			else {
			  echo "<h2>Error Ocurred: ".mysqli_error($conn)."</h2>";
			}
	?>
>

## Register Page (mod_login.php)
>
	<?php 
		require_once('config/init.php');

		$user_email = $_POST['userEmail'];
		$user_pass = md5($_POST['userPass']);

		$sqlLogin = "SELECT * FROM users_acc WHERE userEmail = '".$user_email."' && userPass = '".$user_pass."'";
		$result = mysqli_query($conn,$sqlLogin);
		if (mysqli_num_rows($result) >0) {
			$rowLogin = mysqli_fetch_assoc($result);
			echo "<h2>Hello, ".$row['firstName']." ".$row['lastName']."</h2>";
		}
		else {
			echo "Login Error";
		}
	?>
>
![Thumb](https://user-images.githubusercontent.com/72408025/124386858-fec66080-dce4-11eb-9a91-72da818e067a.png)
