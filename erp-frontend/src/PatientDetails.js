import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import './PatientDetails.css';

const PatientDetails = () => {
    const { id } = useParams();
    const [patient, setPatient] = useState(null);
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
                    toast.error('Acceso denegado. Solo los administradores pueden ver los detalles.', {
                        position: "top-right",
                        autoClose: 3000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                    });
                } else {
                    toast.error('Error al cargar los detalles del paciente. Intenta de nuevo.', {
                        position: "top-right",
                        autoClose: 3000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                    });
                }
                // Redirigir al dashboard después de mostrar el error
                setTimeout(() => {
                    navigate('/admin-dashboard');
                }, 3000);
            }
        };

        fetchPatientDetails();
    }, [id, navigate]);

    const handleBack = () => {
        navigate('/admin-dashboard');
    };

    const handleEdit = () => {
        navigate(`/edit-patient/${id}`);
    };

    const handleViewHistory = () => {
        navigate(`/patient-history/${id}`);
    };

    if (!patient) {
        return <div className="loading-message">Cargando...</div>;
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
            <div className="action-buttons">
                <button onClick={handleBack} className="back-button">
                    Volver al Dashboard
                </button>
                <button onClick={handleViewHistory} className="history-button">
                    Historia Clínica
                </button>
                <button onClick={handleEdit} className="edit-button">
                    Editar información de cliente
                </button>
            </div>
        </div>
    );
};

export default PatientDetails;