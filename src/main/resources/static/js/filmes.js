$(document).ready(function() {
    // Remover filme
    $('.btn-remover').click(function() {
        const id = $(this).data('id');
        if (confirm('Deseja remover este filme?')) {
            $.ajax({
                url: '/filmes/' + id,
                type: 'DELETE',
                success: function() {
                    alert('Filme removido!');
                    location.reload();
                },
                error: function() {
                    alert('Erro ao remover filme.');
                }
            });
        }
    });

    // Editar filme (exemplo simples)
    $('.btn-editar').click(function() {
        const id = $(this).data('id');
        const novoTitulo = prompt('Novo título:');
        if (novoTitulo) {
            $.ajax({
                url: '/filmes/' + id + '/titulo',
                type: 'PATCH',
                contentType: 'application/json',
                data: JSON.stringify({ titulo: novoTitulo }),
                success: function() {
                    alert('Filme atualizado!');
                    location.reload();
                },
                error: function() {
                    alert('Erro ao atualizar filme.');
                }
            });
        }
    });
});

