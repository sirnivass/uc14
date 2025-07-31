function setTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
    
    // Força atualização de cores em elementos específicos
    document.querySelectorAll('.card, .table, .btn-outline-secondary').forEach(element => {
        element.style.transition = 'background-color 0.3s, color 0.3s';
    });
    
    // Atualiza labels do formulário
    document.querySelectorAll('.form-label').forEach(label => {
        label.style.color = isDark ? '#ffffff' : '#212529';
    });
}

function toggleTheme() {
    const currentTheme = localStorage.getItem('theme') || 'light';
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';
    setTheme(newTheme);
}

// Inicializa o tema
document.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme') || 'light';
    setTheme(savedTheme);
    
    // Adiciona transições suaves
    document.body.style.transition = 'background-color 0.3s, color 0.3s';
});