import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './AdminDashboard.css';

const AdminDashboard = () => {
    const [clients, setClients] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchClients = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/clientes', {
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
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        navigate('/');
    };

    return (
        <div className="admin-dashboard">
            <h1>Dashboard Admin</h1>
            <div className="dashboard-actions">
                <button onClick={handleLogout} className="logout-button">
                    Cerrar Sesión
                </button>
                <Link to="/patient-registration" className="register-button">
                    Registrar Nuevo Paciente
                </Link>
            </div>
            <div className="client-list">
                <h2>Clientes</h2>
                <ul>
                    {clients.map(client => (
                        <li key={client.id}>
                            <Link to={`/edit-patient/${client.id}`} className="client-link">
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