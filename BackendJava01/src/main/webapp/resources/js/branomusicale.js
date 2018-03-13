$(function() {
	// su document.ready definisco il comportamento del bottone 'salva'
	$('#btnSalva').click(function() {
		// creo un oggetto JS a partire dai valori inseriti nei campi HTML
		var bm = {
			id: ($('#hdId') == '' ? undefined : $('#hdId').val()),
			titolo: $('#txtTitolo').val(),
			annoPubblicazione: $('#txtAnnoPubblicazione').val(),
			giudizio: $('#txtGiudizio').val()
		}
		// chiamo con $.ajax POST (in caso di inserimento quando l'ID nel campo nascosto hdId
		// è vuoto) o PUT (in caso di modifica quando l'ID nel campo nascosto hdId è valorizzato)
		$.ajax({
			url: '../branomusicale',
			method: ($('#hdId').val() == '' ? 'post' : 'put'),
			data: JSON.stringify(bm),
			contentType: 'application/json'
		})
		.done(function(risposta) {
			// quando è finito $.ajax, loggo in console e svuoto i campi
			if(risposta) {
				console.log('salvato');
			} else {
				console.error('errore');
			}
			$('#hdId').val('');
			$('#txtTitolo').val('');
			$('#txtAnnoPubblicazione').val('');
			$('#txtGiudizio').val('');
			refreshBrani();
		})
	});
	
	// rinfresco la tabella con i brani musicali
	refreshBrani();
});

function refreshBrani() {
	// chiamo l'URL con tutti i brani musicali e riempio la tabella con DataTable
	$.ajax({
		url: '../branomusicale',
		method: 'get'
	}).done(function(brani) {
		$('#tblBrani').DataTable({
			data: brani,
			destroy: true,
			columns: [
				// nella prima colonna metto due pulsanti per gestire eliminazione e modifica
				{title: '', data: null, render: function(data, type, row) {
					var btElimina = '<span class="btn btn-danger delBrano" data-id="' + row.id + '">Elimina</span>';
					var btModifica = ' <span class="btn btn-warning modBrano" data-id="' + row.id + '">Modifica</span>';
					return btElimina + btModifica;
				}},
				{title: 'Titolo', data: 'titolo'},
				{title: 'Anno pubbl.', data: 'annoPubblicazione'},
				{title: 'Giudizio', data: 'giudizio'}
			]
		});
		// aggiungo un gestore su click dei pulsanti 'elimina' nella tabella
		$('.delBrano').click(function() {
			$.ajax({
				url: '../branomusicale/' + $(this).data('id'),
				method: 'delete'
			})
			.done(function(risposta) {
				if(risposta) {
					console.log('eliminato');
				} else {
					console.error('errore');
				}
				refreshBrani();
			})
		});
		// aggiungo un gestore su click dei pulsanti 'modifica' nella tabella
		$('.modBrano').click(function() {
			$.ajax({
				url: '../branomusicale/' + $(this).data('id'),
				method: 'get'
			})
			.done(function(risposta) {
				$('#hdId').val(risposta.id);
				$('#txtTitolo').val(risposta.titolo);
				$('#txtAnnoPubblicazione').val(risposta.annoPubblicazione);
				$('#txtGiudizio').val(risposta.giudizio);
			})
		});
	});
}