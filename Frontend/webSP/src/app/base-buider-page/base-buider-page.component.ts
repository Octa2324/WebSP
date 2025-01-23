import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-base-buider-page',
  templateUrl: './base-buider-page.component.html',
  styleUrl: './base-buider-page.component.css'
})
export class BaseBuiderPageComponent implements OnInit, OnDestroy{
  private unityInstance: any;

  ngOnInit(): void {
    this.loadUnityGame();
  }

  loadUnityGame(): void {
    const buildUrl = 'assets/BaseBuilder';
    const loaderUrl = `${buildUrl}/BaseBuilder Build.loader.js`;

    const config = {
      dataUrl: `${buildUrl}/BaseBuilder Build.data.br`,
      frameworkUrl: `${buildUrl}/BaseBuilder Build.framework.js.br`,
      codeUrl: `${buildUrl}/BaseBuilder Build.wasm.br`,
      streamingAssetsUrl: 'StreamingAssets',
      companyName: 'DefaultCompany',
      productName: 'UnityGame',
      productVersion: '1.0',
      showBanner: (msg: any, type: any) => { console.log(msg, type); },
    };

    const script = document.createElement('script');
    script.src = loaderUrl;
    script.onload = () => {
      (window as any).createUnityInstance(
        document.querySelector('#unity-canvas'),
        config,
        (progress: number) => {
          const progressBarFull = document.querySelector('#unity-progress-bar-full') as HTMLElement;
          if (progressBarFull) progressBarFull.style.width = `${100 * progress}%`;
        }
      ).then((unityInstance: { SetFullscreen: (arg0: number) => void; Quit: () => void; }) => {
        this.unityInstance = unityInstance; 
        const fullscreenButton = document.querySelector('#unity-fullscreen-button');
        if (fullscreenButton) {
          fullscreenButton.addEventListener('click', () => {
            unityInstance.SetFullscreen(1);
          });
        }
      }).catch((message: any) => {
        alert(message);
      });
    };
    document.body.appendChild(script);
  }

  ngOnDestroy(): void {
    if (this.unityInstance) {
      this.unityInstance.Quit(); 
    }
  }
}
