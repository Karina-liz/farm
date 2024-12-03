document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('productoModal');
    const cantidadInput = document.getElementById('modalCantidad');

    // Mostrar datos del producto en el modal
    modal.addEventListener('show.bs.modal', function (event) {
        const card = event.relatedTarget;

        if (!card) return;

        document.getElementById('modalFoto').src = card.getAttribute('data-foto');
        document.getElementById('modalNombreProducto').textContent = card.getAttribute('data-nombre');
        document.getElementById('modalPrecioVenta').textContent = card.getAttribute('data-precio');
        document.getElementById('modalPrincipioActivo').textContent = card.getAttribute('data-principio');
        document.getElementById('modalPresentacion').textContent = card.getAttribute('data-presentacion');
        document.getElementById('modalLaboratorio').textContent = card.getAttribute('data-laboratorio');
    });

    // Incrementar y decrementar la cantidad en el modal
    document.getElementById('btnDecrement').onclick = function () {
        const value = parseInt(cantidadInput.value);
        if (value > 1) cantidadInput.value = value - 1;
    };

    document.getElementById('btnIncrement').onclick = function () {
        const value = parseInt(cantidadInput.value);
        cantidadInput.value = value + 1;
    };

    // Filtrar productos por nombre de búsqueda
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');
    const categorySelect = document.getElementById('categorySelect');

    // Función para actualizar la URL con los parámetros de búsqueda y categoría
    function updateUrl() {
        const searchQuery = searchInput.value; // Obtener el valor de búsqueda
        const selectedCategory = categorySelect.value; // Obtener la categoría seleccionada
        
        let actionUrl = '/catalogo'; // La URL de base

        // Agregar parámetros de búsqueda si existen
        const params = [];
        if (searchQuery) {
            params.push('buscar=' + encodeURIComponent(searchQuery));
        }
        
        if (selectedCategory) {
            params.push('categoria=' + encodeURIComponent(selectedCategory));
        }

        // Si hay parámetros, agregar el "?" y los "&" para concatenarlos correctamente
        if (params.length > 0) {
            actionUrl += '?' + params.join('&');
        }

        // Redirigir a la URL con los parámetros actualizados
        window.location.href = actionUrl;
    }

    // Evento cuando se selecciona una categoría (filtro)
    categorySelect.addEventListener('change', function () {
        updateUrl(); // Actualiza la URL con la categoría seleccionada
    });

    // Evento para manejar la búsqueda con el botón
    searchButton.addEventListener('click', function (event) {
        event.preventDefault(); // Prevenir el envío tradicional del formulario
        updateUrl(); // Actualiza la URL con el valor de búsqueda y categoría
    });

    // Evento para manejar la búsqueda cuando se presiona "Enter"
    searchInput.addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault(); // Prevenir el envío del formulario por defecto
            updateUrl(); // Actualiza la URL con el valor de búsqueda y categoría
        }
    });
});
