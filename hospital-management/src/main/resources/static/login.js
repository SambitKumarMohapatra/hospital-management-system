// Open modal
document.getElementById('loginBtn').addEventListener('click', () => {
  document.getElementById('loginModal').style.display = 'flex';
});

// Close modal
document.getElementById('closeModal').addEventListener('click', () => {
  document.getElementById('loginModal').style.display = 'none';
});

// Close modal when clicking outside
window.addEventListener('click', (event) => {
  const modal = document.getElementById('loginModal');
  if (event.target === modal) {
    modal.style.display = 'none';
  }
});

// Login as specific role
function loginAs(role) {
  if (role === 'doctor') {
    window.location.href = 'doctor.html';
  } else if (role === 'patient') {
    window.location.href = 'patient.html';
  } else if (role === 'admin') {
    window.location.href = 'admin.html';
  } else {
    alert('Please select a role');
  }
}