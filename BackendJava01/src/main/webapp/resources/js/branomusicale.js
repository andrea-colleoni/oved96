$(function() {
	$('#btnSalva').click(function() {
		console.log('salva');
		var bm = {
			id: ($('#hdId') == '' ? undefined : $('#hdId').val()),
			titolo: $('#txtTitolo').val(),
			annoPubblicazione: $('#txtAnnoPubblicazione').val(),
			giudizio: $('#txtGiudizio').val()
		}
		$.ajax({
			url: '../branomusicale',
			method: ($('#hdId').val() == '' ? 'post' : 'put'),
			data: JSON.stringify(bm),
			contentType: 'application/json'
		})
		.done(function(risposta) {
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
	refreshBrani();
});

function refreshBrani() {
	$.ajax({
		url: '../branomusicale',
		method: 'get'
	}).done(function(brani) {
		$('#tblBrani').DataTable({
			data: brani,
			destroy: true,
			columns: [
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