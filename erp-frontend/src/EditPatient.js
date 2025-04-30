import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './EditPatient.css';

const EditPatient = () => {
    const { id } = useParams();
    const [formData, setFormData] = useState({
        cedula: '',
        nombre: '',
        apellido: '',
        telefono: '',
        email: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchPatientDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/clientes/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setFormData({
                    cedula: response.data.cedula,
                    nombre: response.data.nombre,
                    apellido: response.data.apellido,
                    telefono: response.data.telefono || '',
                    email: response.data.email || '',
                });
            } catch (err) {
                console.error('Error al obtener los detalles del paciente:', err);
                if (err.response && err.response.status === 403) {
                    setError('Acceso denegado. Solo los administradores pueden editar pacientes.');
                } else {
                    setError('Error al cargar los datos del paciente. Intenta de nuevo.');
                }
            }
        };

        fetchPatientDetails();
    }, [id]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');

        try {
            await axios.put(`http://localhost:8080/api/clientes/${id}`, formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            alert('Paciente actualizado exitosamente!');
            navigate('/admin-dashboard');
        } catch (err) {
            console.error('Error al actualizar el paciente:', err);
            if (err.response) {
                setError(`Error: ${err.response.data.message || err.response.data || 'No se pudo actualizar el paciente.'}`);
            } else if (err.request) {
                setError('No se recibió respuesta del servidor. Verifica que el backend esté corriendo.');
            } else {
                setError(`Error: ${err.message}`);
            }
        }
    };

    const handleBack = () => {
        navigate('/admin-dashboard');
    };

    if (error) {
        return (
            <div className="error-message">
                {error}
                <button onClick={handleBack} className="back-button">
                    Volver al Dashboard
                </button>
            </div>
        );
    }

    return (
        <div className="edit-patient-container">
            <h2>Editar Paciente</h2>
            <form onSubmit={handleSubmit} className="edit-patient-form">
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
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack} className="back-button">
                        Volver
                    </button>
                    <button type="submit" className="submit-button">
                        Actualizar
                    </button>
                </div>
            </form>
        </div>
    );
};

export default EditPatient;