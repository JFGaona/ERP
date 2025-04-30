import React, { useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './AddHistory.css';

const AddHistory = () => {
    const { id } = useParams();
    const [formData, setFormData] = useState({
        descripcion: '',
        fechaConsulta: '',
        clienteId: id,
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');

        try {
            await axios.post('http://localhost:8080/api/historias', formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            alert('Historia clínica agregada exitosamente!');
            navigate(`/patient-history/${id}`);
        } catch (err) {
            console.error('Error al agregar la historia clínica:', err);
            if (err.response) {
                setError(`Error: ${err.response.data.message || err.response.data || 'No se pudo agregar la historia clínica.'}`);
            } else {
                setError('Error al conectar con el servidor. Intenta de nuevo.');
            }
        }
    };

    const handleBack = () => {
        navigate(`/patient-history/${id}`);
    };

    return (
        <div className="add-history-container">
            <h2>Agregar Historia Clínica</h2>
            {error && <div className="error-message">{error}</div>}
            <form onSubmit={handleSubmit} className="add-history-form">
                <div className="form-group">
                    <label htmlFor="fechaConsulta">Fecha de Consulta:</label>
                    <input
                        type="date"
                        id="fechaConsulta"
                        name="fechaConsulta"
                        value={formData.fechaConsulta}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="descripcion">Descripción:</label>
                    <textarea
                        id="descripcion"
                        name="descripcion"
                        value={formData.descripcion}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack} className="back-button">
                        Volver
                    </button>
                    <button type="submit" className="submit-button">
                        Agregar Historia
                    </button>
                </div>
            </form>
        </div>
    );
};

export default AddHistory;