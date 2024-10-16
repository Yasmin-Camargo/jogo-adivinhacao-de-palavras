// theme.ts
import { extendTheme } from '@chakra-ui/react';

const theme = extendTheme({
  styles: {
    global: {
      body: {
        bg: '#21003b', // Gradiente do fundo
      },
    },
  },
});

export default theme;
