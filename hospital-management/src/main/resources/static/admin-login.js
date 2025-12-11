// Admin Login JavaScript
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('login-form');
    const errorDiv = document.getElementById('error-message');

    // Clear any existing authentication when loading login page
    sessionStorage.removeItem('adminAuthenticated');
    sessionStorage.removeItem('adminEmail');
    sessionStorage.removeItem('loginTime');

    // Focus on email input when page loads
    document.getElementById('email').focus();

    // Form submission handler
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;

        // Hide any previous error
        errorDiv.style.display = 'none';

        // Basic validation
        if (!email || !password) {
            showError('Please enter both email and password.');
            return;
        }

        // Check credentials (hardcoded for demo)
        if (authenticateAdmin(email, password)) {
            // Store authentication data
            sessionStorage.setItem('adminAuthenticated', 'true');
            sessionStorage.setItem('adminEmail', email);
            sessionStorage.setItem('loginTime', new Date().toISOString());

            // Redirect to admin dashboard
            console.log('✅ Admin login successful, redirecting...');
            window.location.href = 'admin.html';
        } else {
            showError('Invalid email or password. Please try again.');
            // Clear password field
            document.getElementById('password').value = '';
            document.getElementById('password').focus();
        }
    });

    // Authentication function
    function authenticateAdmin(email, password) {
        // Hardcoded credentials for demo
        const validCredentials = [
            { email: 'admin.sambit@hospital.com', password: 'adminpass' },
            { email: 'sk02mohapatra@gmail.com', password: 'sambit123' }
        ];

        return validCredentials.some(cred =>
            cred.email === email && cred.password === password
        );
    }

    // Show error message
    function showError(message) {
        errorDiv.textContent = message;
        errorDiv.style.display = 'block';

        // Auto-hide error after 5 seconds
        setTimeout(() => {
            errorDiv.style.display = 'none';
        }, 5000);
    }

    // Enter key support - submit form when pressing Enter
    document.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            loginForm.dispatchEvent(new Event('submit'));
        }
    });

    // Demo credentials auto-fill for testing (optional)
    console.log('🔐 Admin Login Page Loaded');
    console.log('Available demo credentials:');
    console.log('   Email: admin@hospital.com, Password: admin123');
    console.log('   Email: superadmin@hospital.com, Password: super123');
});