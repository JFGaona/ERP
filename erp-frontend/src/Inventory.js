import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './Inventory.css';

const Inventory = () => {
    const [marcos, setMarcos] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const fetchMarcos = async () => {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.get('http://localhost:8080/api/marcos', {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setMarcos(response.data);
        } catch (err) {
            console.error('Error al cargar los marcos:', err);
            if (err.response && err.response.status === 403) {
                setError('Acceso denegado. Solo los administradores pueden ver el inventario.');
            } else {
                setError('Error al cargar el inventario. Intenta de nuevo.');
            }
        }
    };

    useEffect(() => {
        fetchMarcos();
    }, []);

    const handleDelete = async (id) => {
        const token = localStorage.getItem('token');
        if (window.confirm('¿Estás seguro de que deseas eliminar este marco?')) {
            try {
                await axios.delete(`http://localhost:8080/api/marcos/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setMarcos(marcos.filter(marco => marco.id !== id));
                alert('Marco eliminado exitosamente.');
            } catch (err) {
                console.error('Error al eliminar el marco:', err);
                setError('Error al eliminar el marco. Intenta de nuevo.');
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
        <div className="inventory-container">
            <h2>Inventario de Marcos de Lentes</h2>
            <div className="inventory-actions">
                <Link to="/add-marco" className="add-button">
                    Agregar Nuevo Marco
                </Link>
                <button onClick={handleBack} className="back-button">
                    Volver al Dashboard
                </button>
            </div>
            {marcos.length === 0 ? (
                <p>No hay marcos registrados en el inventario.</p>
            ) : (
                <table className="inventory-table">
                    <thead>
                    <tr>
                        <th>Modelo</th>
                        <th>Marca</th>
                        <th>Descripción</th>
                        <th>Cantidad Disponible</th>
                        <th>Precio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    {marcos.map(marco => (
                        <tr key={marco.id}>
                            <td>{marco.modelo}</td>
                            <td>{marco.marca || 'N/A'}</td>
                            <td>{marco.descripcion || 'N/A'}</td>
                            <td>{marco.cantidadDisponible}</td>
                            <td>{marco.precio ? `$${marco.precio}` : 'N/A'}</td>
                            <td>{marco.estado || 'N/A'}</td>
                            <td>
                                <Link to={`/edit-marco/${marco.id}`} className="edit-action">
                                    Editar
                                </Link>
                                <button
                                    onClick={() => handleDelete(marco.id)}
                                    className="delete-action"
                                >
                                    Eliminar
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default Inventory;