import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Login from './Login';
import AdminDashboard from './AdminDashboard';
import UserDashboard from './UserDashboard';
import PatientRegistration from './PatientRegistration';
import PatientDetails from './PatientDetails';
import EditPatient from './EditPatient';
import Inventory from './Inventory';
import AddMarco from './AddMarco';
import EditMarco from './EditMarco';
import PatientHistory from './PatientHistory';
import AddHistory from './AddHistory';
import ProtectedRoute from './ProtectedRoute';
import './App.css'; // Asegúrate de importar el archivo CSS

function App() {
    return (
        <div className="App">
            <Router>
                <ToastContainer
                    position="top-right"
                    autoClose={3000}
                    hideProgressBar={false}
                    newestOnTop={false}
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover
                    theme="light"
                />
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
                    <Route
                        path="/inventory"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <Inventory />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/add-marco"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <AddMarco />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/edit-marco/:id"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <EditMarco />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/patient-history/:id"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <PatientHistory />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/add-history/:id"
                        element={
                            <ProtectedRoute requiredRole="ADMIN">
                                <AddHistory />
                            </ProtectedRoute>
                        }
                    />
                </Routes>
            </Router>
        </div>
    );
}

export default App;