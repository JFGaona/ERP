import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import Breadcrumb from "./Breadcrumb";
import './AdminDashboard.css';

const AdminDashboard = () => {
    const [clients, setClients] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchClients = async () => {
            try {
                const endpoint = searchTerm
                    ? `http://localhost:8080/api/clientes/search/by-term?termino=${encodeURIComponent(searchTerm)}`
                    : 'http://localhost:8080/api/clientes';
                const response = await axios.get(endpoint, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setClients(response.data);
            } catch (err) {
                console.error('Error al cargar los clientes:', err);
            }
        };

        fetchClients();
    }, [searchTerm]);

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        navigate('/');
    };

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    return (
        <div className="admin-dashboard">
            <Breadcrumb />
            <h1>Panel De Administrador</h1>
            <div className="dashboard-actions">
                <button onClick={handleLogout} className="logout-button">
                    Cerrar Sesión
                </button>
                <Link to="/inventory" className="inventory-button">
                    Inventario
                </Link>
                <Link to="/patient-registration" className="register-button">
                    Registrar Nuevo Paciente
                </Link>
            </div>
            <div className="client-list">
                <div className="client-list-header">
                    <h2>Clientes</h2>
                    <input
                        type="text"
                        placeholder="Buscar por nombre o cédula..."
                        value={searchTerm}
                        onChange={handleSearchChange}
                        className="search-input"
                    />
                </div>
                <ul>
                    {clients.map(client => (
                        <li key={client.id}>
                            <Link to={`/patient-details/${client.id}`} className="client-link">
                                {client.nombre} {client.apellido} - {client.cedula}
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default AdminDashboard;