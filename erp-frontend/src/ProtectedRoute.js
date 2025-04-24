import React from 'react';
import { Navigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const ProtectedRoute = ({ children, requiredRole }) => {
    const token = localStorage.getItem('token');
    if (!token) {
        return <Navigate to="/" />;
    }

    try {
        const decoded = jwtDecode(token);
        const currentTime = Date.now() / 1000;
        if (decoded.exp < currentTime) {
            localStorage.removeItem('token');
            return <Navigate to="/" />;
        }
        const userRole = decoded.role;
        if (requiredRole && userRole !== requiredRole) {
            return <Navigate to="/" />;
        }
        return children;
    } catch (error) {
        localStorage.removeItem('token');
        return <Navigate to="/" />;
    }
};

export default ProtectedRoute;