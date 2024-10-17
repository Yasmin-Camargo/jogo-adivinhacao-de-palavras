import { useState } from 'react';
import axios from 'axios';

const API_URL = "http://localhost:8080/game";

export function useGame() {
  const [word, setWord] = useState('');  
  const [message, setMessage] = useState('');  

  const startGame = async () => {
    try {
      const response = await axios.get(`${API_URL}/start`);
      return response.data;
    } catch (error) {
      console.error('Erro ao iniciar o jogo:', error);
      throw error; 
    }
  };

  const checkWord = async (word:string) => {
    try {
      const response = await axios.get(`${API_URL}/check/${word}`);
      setMessage(response.data);  
    } catch (error) {
      console.error('Erro ao verificar a palavra:', error);
      setMessage('Erro ao verificar a palavra.');
    }
  };

  return {
    word,
    setWord,
    message,
    startGame,
    checkWord,
  };
}
