import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AdminDashboard = () => {
    const [clientes, setClientes] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchClientes = async () => {
            try {
                const token = localStorage.getItem('token');
                if (!token) {
                    navigate('/');
                    return;
                }
                const response = await axios.get('http://localhost:8080/api/clientes', {
                    headers: { Authorization: `Bearer ${token}` }
                });
                setClientes(response.data);
            } catch (err) {
                if (err.response && err.response.status === 401) {
                    localStorage.removeItem('token');
                    navigate('/');
                } else {
                    setError('Error al cargar los clientes. Intenta de nuevo.');
                }
            }
        };
        fetchClientes();
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/');
    };

    return (
        <div>
            <h1>Dashboard Admin</h1>
            <button onClick={handleLogout} style={{ marginBottom: '1rem' }}>
                Cerrar Sesión
            </button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <h2>Clientes</h2>
            <ul>
                {clientes.map(cliente => (
                    <li key={cliente.id}>{cliente.nombre} {cliente.apellido} - {cliente.cedula}</li>
                ))}
            </ul>
        </div>
    );
};

export default AdminDashboard;