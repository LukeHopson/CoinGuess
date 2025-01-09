$(document).ready(function () {
    $('#dropdown').select2({
      dropdownAutoWidth: true,
      width: '100%',
      dropdownParent: $('.card') // Prevent "drop up"
    });
});


// https://www.youtube.com/watch?v=zUeG6VgdKSc