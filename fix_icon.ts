import { Jimp } from 'jimp';

async function fixIcon() {
  try {
    console.log('Reading icon...');
    const image = await Jimp.read('public/assets/icon.png');
    console.log('Writing fixed icon...');
    await image.writeAsync('public/assets/icon_fixed.png');
    console.log('Done!');
  } catch (err) {
    console.error('Error fixing icon:', err);
  }
}

fixIcon();
