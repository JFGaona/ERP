import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './PatientRegistration.css';

const PatientRegistration = () => {
    const [formData, setFormData] = useState({
        cedula: '',
        nombre: '',
        apellido: '',
        telefono: '',
        email: '',
    });
    const [isAdmin, setIsAdmin] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    // Check if the user is an admin on component mount
    useEffect(() => {
        const token = localStorage.getItem('token');
        const userRole = localStorage.getItem('role');

        console.log('User role from localStorage:', userRole);
        console.log('Token:', token);

        if (!token) {
            navigate('/login');
            return;
        }

        if (!userRole || userRole.toLowerCase() !== 'admin') {
            setError('Access denied. Only admins can register patients.');
            return;
        }

        setIsAdmin(true);
    }, [navigate]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');

        console.log('Enviando datos al backend:', formData);

        try {
            const response = await axios.post('http://localhost:8080/api/clientes', formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log('Respuesta del backend:', response.data);
            alert('Patient registered successfully!');
            setFormData({ cedula: '', nombre: '', apellido: '', telefono: '', email: '' });
        } catch (err) {
            console.error('Error al registrar paciente:', err);
            if (err.response) {
                setError(`Error: ${err.response.data.message || err.response.data || 'No se puede registrar paciente, intenta nuevamente.'}`);
            } else if (err.request) {
                setError('No se recibió respuesta del servidor. Verifica que el backend esté corriendo en http://localhost:8080.');
            } else {
                setError(`Error: ${err.message}`);
            }
        }
    };

    const handleBack = () => {
        navigate('/admin-dashboard');
    };

    if (!isAdmin) {
        return <div className="error-message">{error}</div>;
    }

    return (
        <div className="patient-registration-container">
            <h2>Registrar Paciente Nuevo</h2>
            {error && <div className="error-message">{error}</div>}
            <form onSubmit={handleSubmit} className="patient-form">
                <div className="form-group">
                    <label htmlFor="cedula">Cédula:</label>
                    <input
                        type="text"
                        id="cedula"
                        name="cedula"
                        value={formData.cedula}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="nombre">Nombre:</label>
                    <input
                        type="text"
                        id="nombre"
                        name="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="apellido">Apellido:</label>
                    <input
                        type="text"
                        id="apellido"
                        name="apellido"
                        value={formData.apellido}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="telefono">Teléfono:</label>
                    <input
                        type="text"
                        id="telefono"
                        name="telefono"
                        value={formData.telefono}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Correo:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack} className="back-button">
                        Volver
                    </button>
                    <button type="submit" className="submit-button">
                        Registrar Paciente
                    </button>
                </div>
            </form>
        </div>
    );
};

export default PatientRegistration;