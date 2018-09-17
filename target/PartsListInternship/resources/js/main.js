function submitPartForm() {
    var partname = $('#partname').val().trim();
    var partbase = $('#partbase').val();
    var partqty = $('#partqty').val();
    var parttype = $('#parttype').val();
    if(partname.length === 0) {
        alert('Please enter name part');
        $('partname').focus();
        return false;
    }
    if(partqty < 0) {
        alert('Qty does not be minusable');
        $('partqty').focus();
        return false;
    }
    if(partbase !== 'true' && partbase !== 'false') {
        alert('For partbase status, enter 0 (for false) or 1 (for true)');
        $('partbase').focus();
        return false;
    }
    if(parttype > 6 ) {
        alert('Тип детали должен лежать в диапазоне от 1 до 6!');
        $('parttype').focus();
        return false;
    }
    if(parttype < 1 ) {
        alert('Тип детали должен лежать в диапазоне от 1 до 6!');
        $('parttype').focus();
        return false;
    }

    return true;
}

