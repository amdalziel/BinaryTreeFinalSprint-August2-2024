document.getElementById('postNewBST').addEventListener('click', () => {

    const input = document.getElementById('nodeValues').value;

    const intValues = input.split(',').map(v => parseInt(v.trim()));
    console.log(intValues);


    fetch('/process-numbers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(intValues)
    })
    .then(response => response.json())
    .then(result => {
        document.getElementById('result').textContent = JSON.stringify(result, null, 2);
    })
    .catch(error => console.error('Error:', error));
});