const baseUrl = window.location.protocol + '//' + (window.location.href).match('^(?:http:\/\/|www\.|https:\/\/)([^\/]+)')[1];

$('#submitTotal').on('click', () => {
    const total = $('#totalComponent').val();
    addComponents(total);
    addResultTotal();
    addCalculateEvent();
    addReturnEvent();
});

const addComponents = (totalComponent = 0) => {
    let components = `
        <div id="components">
            <h2>Komponen Penilaian</h2>
    `;

    for (let i = 0; i < totalComponent; i++) {
        components += `
            <div id="component${i}" class="component">
                <input type="number" class="score" min="0" placeholder="0"> x
                <input type="number" class="weight" min="0" placeholder="0">%
            </div>
        `;
    }

    components += `
            <button type="submit" id="calculate" class="button">Calculate</button>
            <button type="submit" id="return" class="button">Return</button>
        </div>
    `;

    $('#main').empty().append(components);
};

const addResultTotal = () => {
    const components = `
        <h2>Data Nilai</h2>
        <h3>Total Bobot: <span id="totalWeight">0</span>%</h3>
        <h3>Total Nilai Berbobot: <span id="totalScore">0</span></h3>
        <h3>Nilai Akhir: <span id="finalScore">...</span></h3>
    `;

    $('#result').empty().append(components);
};

const addCalculateEvent = () => {
    $('#calculate').on('click', () => {
        let scoreList = [];
        let weightList = [];
        $('.component').each(function() {
            let score = $(this).children().get(0).value;
            let weight = $(this).children().get(1).value;
            score = (score === "") ? 0 : score;
            weight = (weight === "") ? 0 : weight;
            scoreList.push(score);
            weightList.push(weight);
        });

        $.ajax({
            method: 'GET',
            url: `${baseUrl}/scoretracker/track?scoreList=${scoreList}&weightList=${weightList}`,
            dataType: 'json',
            success: function (response) {
                showResult(response);
            },
            failed: function () {
                alert('There was an error when fetching the data, please try again later!');
            }
        })
    });
};

const showResult = (result) => {
    $('#totalWeight').text(result.weight);
    $('#totalScore').text(result.score);
    $('#finalScore').text(result.grade);
}

const addReturnEvent = () => {
    $('#return').on('click', () => {
        location.reload();
    });
};