function updatePreview(input, target) {
    let file = input.files[0];
    let reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = function () {
        let img = document.getElementById(target);
        // can also use "this.result"
        img.src = reader.result;
    }
}