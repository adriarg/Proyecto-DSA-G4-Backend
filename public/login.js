// Asume que ya tienes el formulario HTML con id="loginForm", y dos campos: username y password
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevenir el comportamiento por defecto de recargar la página

    // Obtener el valor de los campos del formulario
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // Hacer la petición POST con las credenciales del usuario
    fetch('http://localhost:8080/usuarios/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ nombre: username, contraseña: password })
    })
    .then(response => {
        if (response.status === 200) {
            // Si el login es exitoso, redirigir a la página deseada
            window.location.href = "http://localhost:8080/tienda.html";  // Redirige a tienda.html
        } else {
            alert('Credenciales incorrectas');
        }
    })
    .catch(error => {
        console.error('Error al intentar iniciar sesión:', error);
    });
});
