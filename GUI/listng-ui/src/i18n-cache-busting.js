const fs = require('fs');
const crypto = require('crypto');
const glob = require('glob');

const result = {};
const jsonFilesDir = './src/assets/i18n/';
const jsonSpace = 2;

function generateChecksum(str, algorithm, encoding) {
return crypto
.createHash(algorithm || 'md5')
.update(str, 'utf8')
.digest(encoding || 'hex');
}

glob.sync(`${jsonFilesDir}**/*.json`).forEach(path => {
const lang = path.split(jsonFilesDir)[1];
const content = fs.readFileSync(path, {encoding: 'utf-8'});
result[lang.replace('.json', '')] = generateChecksum(content);
});

fs.writeFileSync('./i18n-cache-busting.json', JSON.stringify(result, null, jsonSpace));
