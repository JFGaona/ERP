import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import './EditMarco.css';

const EditMarco = () => {
    const { id } = useParams();
    const [formData, setFormData] = useState({
        modelo: '',
        marca: '',
        descripcion: '',
        cantidadDisponible: '',
        precio: '',
        estado: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchMarco = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/marcos/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setFormData({
                    modelo: response.data.modelo,
                    marca: response.data.marca || '',
                    descripcion: response.data.descripcion || '',
                    cantidadDisponible: response.data.cantidadDisponible,
                    precio: response.data.precio,
                    estado: response.data.estado,
                });
            } catch (err) {
                console.error('Error al cargar el marco:', err);
                setError('Error al cargar los datos del marco. Intenta de nuevo.');
            }
        };

        fetchMarco();
    }, [id]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');

        try {
            await axios.put(`http://localhost:8080/api/marcos/${id}`, formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            alert('Marco actualizado exitosamente!');
            navigate('/inventory');
        } catch (err) {
            console.error('Error al actualizar el marco:', err);
            if (err.response) {
                setError(`Error: ${err.response.data.message || err.response.data || 'No se pudo actualizar el marco.'}`);
            } else {
                setError('Error al conectar con el servidor. Intenta de nuevo.');
            }
        }
    };

    const handleBack = () => {
        navigate('/inventory');
    };

    if (error) {
        return (
            <div className="error-message">
                {error}
                <button onClick={handleBack} className="back-button">
                    Volver al Inventario
                </button>
            </div>
        );
    }

    return (
        <div className="edit-marco-container">
            <h2>Editar Marco</h2>
            <form onSubmit={handleSubmit} className="edit-marco-form">
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
                        Actualizar Marco
                    </button>
                </div>
            </form>
        </div>
    );
};

export default EditMarco;