<style>
html {
	height: 100%;
	background-color: white;
}

body {
	margin: 0 auto;
	height: 100%;
	width: 80%;
	background-color: grey;
}

#form {
	margin: 0 auto;
	width: 60%;
}

#table {
	margin: 0 auto;
	width: 100%;
}

h2 {
	text-align: center;
}
</style>
<script>
	$(function() {

		var name_err = $("#name_err");
		var phone_err = $("#phone_err");
		var email_err = $("#email_err");
		var password_err = $("#password_err");

		var submit = false;

		name_err.hide();
		phone_err.hide();
		email_err.hide();
		password_err.hide();

		$("#name").focusout(function() {
			checkName($(this).val().length);
		})

		$("#phone").focusout(function() {
			checkPhone($(this));
		})
		$("#email").focusout(function() {
			checkEmail($(this));
		})
		$("#password").focusout(function() {
			checkPswd($(this));
		})

		$("#form").submit(function() {
			if (submit) {
				return true;
			} else {
				return false;
			}
		})

		function checkName(element_length) {
			if (element_length < 4) {
				name_err.show();
				submit = false;
			} else {
				name_err.hide();
				submit = true;
			}
		}

		function checkPhone(element) {
			var pattern = new RegExp('^[0-9]+$');
			if (element.val().length > 5 && pattern.test(element.val().trim())) {
				phone_err.hide();
				submit = true;
			} else {
				phone_err.show();
				submit = false;

			}
		}

		function checkEmail(element) {
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			if (re.test(element.val().trim())) {
				email_err.hide();
				submit = true;
			} else {
				email_err.show();
				submit = false;
			}

		}

		function checkPswd(element) {
			var re = (/^(?=.*\d)(?=.*[a-z])[0-9a-zA-Z]{5,}$/);
			if (re.test(element.val().trim())) {
				password_err.hide();
				submit = true;
			} else {
				password_err.show();
				submit = false;
			}
		}

	})
</script>












