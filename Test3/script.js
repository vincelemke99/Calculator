document.querySelectorAll('#calculator-container  button').forEach(button => {
    button.addEventListener('click', () => {
        const value = button.textContent;
        const resultArea = document.getElementById('resultArea');

        if (value === 'AC') {
            resultArea.textContent = '0'; // Resets the display
        } else if (value === 'DEL') {
            resultArea.textContent = resultArea.textContent.slice(0, -1); // Deletes last character
        } else if (value === '=') {
            try {
                resultArea.textContent = eval(resultArea.textContent); // Calculates the result
            } catch (error) {
                resultArea.textContent = 'Error'; // Handles any calculation errors
            }
        } else {
            if (resultArea.textContent === '0') {
                resultArea.textContent = value; // Starts new input
            } else {
                resultArea.textContent += value; // Appends the value
            }
        }
    });
});
