import React from 'react';
import { Navigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';

const ProtectedRoute = ({ children, requiredRole }) => {
    const token = localStorage.getItem('token');
    if (!token) {
        return <Navigate to="/" />;
    }
    const decoded = jwtDecode(token);
    const userRole = decoded.role;
    if (requiredRole && userRole !== requiredRole) {
        return <Navigate to="/" />;
    }
    return children;
};

export default ProtectedRoute;