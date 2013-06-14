function switchAll(obj) {
		$('input.onDelete').each(function() {
			this.checked = obj.checked;
		});
}

function uncheckSelectAll(obj) {
	if (!obj.checked) {
		$('input#selectAll')[0].checked = false;
	}
}

function requiredFieldCheck(inputText, outputText) {
	if (inputText.value.length <= 2) {
		outputText.innerHTML = 'Обязательное поле!';
	} else {
		outputText.innerHTML = "";
	}
}