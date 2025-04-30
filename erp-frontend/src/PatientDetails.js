import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './PatientDetails.css';

const PatientDetails = () => {
    const { id } = useParams(); // Obtener el ID del cliente desde la URL
    const [patient, setPatient] = useState(null);
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
                setPatient(response.data);
            } catch (err) {
                console.error('Error al obtener los detalles del paciente:', err);
                if (err.response && err.response.status === 403) {
                    setError('Acceso denegado. Solo los administradores pueden ver los detalles.');
                } else {
                    setError('Error al cargar los detalles del paciente. Intenta de nuevo.');
                }
            }
        };

        fetchPatientDetails();
    }, [id]);

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

    if (!patient) {
        return <div>Cargando...</div>;
    }

    return (
        <div className="patient-details-container">
            <h2>Detalles del Paciente</h2>
            <div className="patient-info">
                <p><strong>Cédula:</strong> {patient.cedula}</p>
                <p><strong>Nombre:</strong> {patient.nombre}</p>
                <p><strong>Apellido:</strong> {patient.apellido}</p>
                <p><strong>Teléfono:</strong> {patient.telefono || 'No disponible'}</p>
                <p><strong>Email:</strong> {patient.email || 'No disponible'}</p>
                <p><strong>Fecha de Registro:</strong> {patient.fechaRegistro || 'No disponible'}</p>
            </div>
            <button onClick={handleBack} className="back-button">
                Volver al Dashboard
            </button>
        </div>
    );
};

export default PatientDetails;