import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AddMarco.css';

const AddMarco = () => {
    const [formData, setFormData] = useState({
        modelo: '',
        marca: '',
        descripcion: '',
        cantidadDisponible: '',
        precio: '',
        estado: 'DISPONIBLE',
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
            await axios.post('http://localhost:8080/api/marcos', formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            alert('Marco agregado exitosamente!');
            navigate('/inventory');
        } catch (err) {
            console.error('Error al agregar el marco:', err);
            if (err.response) {
                setError(`Error: ${err.response.data.message || err.response.data || 'No se pudo agregar el marco.'}`);
            } else {
                setError('Error al conectar con el servidor. Intenta de nuevo.');
            }
        }
    };

    const handleBack = () => {
        navigate('/inventory');
    };

    return (
        <div className="add-marco-container">
            <h2>Agregar Nuevo Marco</h2>
            {error && <div className="error-message">{error}</div>}
            <form onSubmit={handleSubmit} className="add-marco-form">
                <div className="form-group">
                    <label htmlFor="modelo">Modelo:</label>
                    <input
                        type="text"
                        id="modelo"
                        name="modelo"
                        value={formData.modelo}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="marca">Marca:</label>
                    <input
                        type="text"
                        id="marca"
                        name="marca"
                        value={formData.marca}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="descripcion">Descripción:</label>
                    <textarea
                        id="descripcion"
                        name="descripcion"
                        value={formData.descripcion}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="cantidadDisponible">Cantidad Disponible:</label>
                    <input
                        type="number"
                        id="cantidadDisponible"
                        name="cantidadDisponible"
                        value={formData.cantidadDisponible}
                        onChange={handleChange}
                        min="0"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="precio">Precio:</label>
                    <input
                        type="number"
                        id="precio"
                        name="precio"
                        value={formData.precio}
                        onChange={handleChange}
                        min="0.01"
                        step="0.01"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="estado">Estado:</label>
                    <select
                        id="estado"
                        name="estado"
                        value={formData.estado}
                        onChange={handleChange}
                        required
                    >
                        <option value="DISPONIBLE">Disponible</option>
                        <option value="AGOTADO">Agotado</option>
                    </select>
                </div>
                <div className="form-actions">
                    <button type="button" onClick={handleBack} className="back-button">
                        Volver
                    </button>
                    <button type="submit" className="submit-button">
                        Agregar Marco
                    </button>
                </div>
            </form>
        </div>
    );
};

export default AddMarco;