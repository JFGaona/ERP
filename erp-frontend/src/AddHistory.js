import React, { useState } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import './AddHistory.css';

const AddHistory = () => {
    const { id } = useParams();
    const [formData, setFormData] = useState({
        descripcion: '',
        fechaConsulta: '',
        clienteId: id,
    });
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

            // Mostrar notificación de éxito con react-toastify
            toast.success('¡Historia clínica agregada exitosamente!', {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });

            // Redirigir después de 3 segundos
            setTimeout(() => {
                navigate(`/patient-history/${id}`);
            }, 3000);
        } catch (err) {
            console.error('Error al agregar la historia clínica:', err);
            let errorMessage = 'Error al conectar con el servidor. Intenta de nuevo.';
            if (err.response) {
                errorMessage = err.response.data.message || err.response.data || 'No se pudo agregar la historia clínica.';
            }

            // Mostrar notificación de error con react-toastify
            toast.error(`Error: ${errorMessage}`, {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
    };

    const handleBack = () => {
        navigate(`/patient-history/${id}`);
    };

    return (
        <div className="add-history-container">
            <div className="add-history-box">
                <h2>Agregar Historia Clínica</h2>
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
                            placeholder="Describe la consulta..."
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
        </div>
    );
};

export default AddHistory;