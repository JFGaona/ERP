import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate, Link } from 'react-router-dom';
import './PatientHistory.css';

const PatientHistory = () => {
    const { id } = useParams();
    const [histories, setHistories] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchHistories = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/historias/cliente/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setHistories(response.data);
            } catch (err) {
                console.error('Error al cargar las historias clínicas:', err);
                if (err.response && err.response.status === 403) {
                    setError('Acceso denegado. Solo los administradores pueden ver las historias clínicas.');
                } else {
                    setError('Error al cargar las historias clínicas. Intenta de nuevo.');
                }
            }
        };

        fetchHistories();
    }, [id]);

    const handleBack = () => {
        navigate(`/patient-details/${id}`);
    };

    if (error) {
        return (
            <div className="error-message">
                {error}
                <button onClick={handleBack} className="back-button">
                    Volver a Detalles del Paciente
                </button>
            </div>
        );
    }

    return (
        <div className="patient-history-container">
            <h2>Historia Clínica del Paciente</h2>
            <div className="history-actions">
                <Link to={`/add-history/${id}`} className="add-button">
                    Agregar Historia Clínica
                </Link>
                <button onClick={handleBack} className="back-button">
                    Volver a Detalles del Paciente
                </button>
            </div>
            {histories.length === 0 ? (
                <p>No hay historias clínicas registradas para este paciente.</p>
            ) : (
                <table className="history-table">
                    <thead>
                    <tr>
                        <th>Fecha de Consulta</th>
                        <th>Descripción</th>
                    </tr>
                    </thead>
                    <tbody>
                    {histories.map(history => (
                        <tr key={history.id}>
                            <td>{history.fechaConsulta}</td>
                            <td>{history.descripcion}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default PatientHistory;