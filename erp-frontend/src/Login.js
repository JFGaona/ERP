import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import './Login.css';
import logoOptica from './assets/logo-optica.png';

const Login = () => {
    const [email, setEmail] = useState('anfeji98@hotmail.com');
    const [password, setPassword] = useState('pass123');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', { email, password });
            const token = response.data.accessToken;
            localStorage.setItem('token', token);
            const decoded = jwtDecode(token);

            const role = decoded.role;
            console.log('Decoded role from token:', role);
            const normalizedRole = role.charAt(0).toUpperCase() + role.slice(1).toLowerCase();
            localStorage.setItem('role', normalizedRole);
            if (normalizedRole === 'Admin') {
                navigate('/admin-dashboard');
            } else {
                navigate('/user-dashboard');
            }
        } catch (err) {
            if (err.response && err.response.data) {
                setError(err.response.data || 'Error al iniciar sesión. Verifica tus credenciales.');
            } else {
                setError('Error al conectar con el servidor. Intenta de nuevo.');
            }
        } finally {
            setLoading(false);
        }
    };

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div className="login-container">
            <div className="login-box">
                <img src={logoOptica} alt="Óptica Provisual" className="login-logo" />
                <h2>Iniciar Sesión</h2>
                {error && <p className="error-message">{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="input-group">
                        <label htmlFor="email">Correo Electrónico</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="tu@email.com"
                            required
                        />
                    </div>
                    <div className="input-group password-group">
                        <label htmlFor="password">Contraseña</label>
                        <div className="password-input-container">
                            <input
                                type={showPassword ? 'text' : 'password'}
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="••••••••"
                                required
                            />
                            <span className="password-toggle" onClick={togglePasswordVisibility}>
                                {showPassword ? <FaEyeSlash /> : <FaEye />}
                            </span>
                        </div>
                    </div>
                    <button type="submit" className="login-button" disabled={loading}>
                        {loading ? 'Cargando...' : 'Iniciar Sesión'}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default Login;