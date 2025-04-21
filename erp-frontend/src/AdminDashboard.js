import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminDashboard = () => {
    const [clientes, setClientes] = useState([]);

    useEffect(() => {
        const fetchClientes = async () => {
            const token = localStorage.getItem('token');
            const response = await axios.get('http://localhost:8080/api/clientes', {
                headers: { Authorization: `Bearer ${token}` }
            });
            setClientes(response.data);
        };
        fetchClientes();
    }, []);

    return (
        <div>
            <h1>Dashboard Admin</h1>
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