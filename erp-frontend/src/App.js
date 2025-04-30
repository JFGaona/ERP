import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login';
import AdminDashboard from './AdminDashboard';
import UserDashboard from './UserDashboard';
import PatientRegistration from './PatientRegistration';
import PatientDetails from './PatientDetails';
import EditPatient from './EditPatient';
import ProtectedRoute from './ProtectedRoute';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route
                    path="/admin-dashboard"
                    element={
                        <ProtectedRoute requiredRole="ADMIN">
                            <AdminDashboard />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/user-dashboard"
                    element={
                        <ProtectedRoute requiredRole="USER">
                            <UserDashboard />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/patient-registration"
                    element={
                        <ProtectedRoute requiredRole="ADMIN">
                            <PatientRegistration />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/patient-details/:id"
                    element={
                        <ProtectedRoute requiredRole="ADMIN">
                            <PatientDetails />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/edit-patient/:id"
                    element={
                        <ProtectedRoute requiredRole="ADMIN">
                            <EditPatient />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;